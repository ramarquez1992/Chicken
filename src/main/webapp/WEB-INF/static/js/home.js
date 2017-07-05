var currUser = {};
var currRound = null;
var isChick1Stream = false;
var isChick2Stream = false;
var currUserStreamCtrl = {};
var chick1StreamCtrl = {};
var chick2StreamCtrl = {};
var chick1StreamEmail;
var chick2StreamEmail;
var alreadyVoted = false;
var confirmed = false;
var currRoundId = 0;
var qAble = false;
var setReadyDelay = 4000;



function resetTimer(secondsRemaining) {
    timeRemaining = secondsRemaining;

    try {
        clearInterval(timerInterval);
    } catch (e) {
        //
    }

    timerInterval = setInterval(function() {
        updateTimer();
    }, 1000);

    console.log('TIME REMAINING: ' + timeRemaining);
}

function updateTimer() {
    var secondsEl = $('#timerContainer #seconds');

    if (timeRemaining > 0) {
        secondsEl.text(timeRemaining);
        timeRemaining--;
    } else {
        secondsEl.text('---');
    }
}



function refreshSpotlightDisplay() {
    if (currRound == null || !currRound.started) {
        $('#waitingContainer').show();
        $('#spotlightContainer').hide();
    } else {
        $('#waitingContainer').hide();
        $('#spotlightContainer').show();
    }
}

app.controller('SpotlightController', function ($scope) {


    getSelf(function (cu) {
        currUser = cu;

        var socket = new WebSocket('ws://' + window.location.hostname + ':8443/sock');
        var stompClient = Stomp.over(socket);

        stompClient.connect({email: cu.email}, function (frame) {
            stompClient.subscribe('/topic/messages', function (res) {
                var newRound = JSON.parse(res.body);

                resetTimer(newRound.secondsRemaining);

                // Show/hide "You are a chick"
                if ( (newRound.chick1 != null && newRound.chick1.id == currUser.id) ||
                     (newRound.chick2 != null && newRound.chick2.id == currUser.id)
                ){
                    $('#youAreAChickContainer').show();
                } else {
                    $('#youAreAChickContainer').hide();
                }


                // Don't show your own stream
                if (newRound.chick1 != null) {
                    if ( currUser.id == newRound.chick1.id) {
                        $('#chick1').hide();
                        $('#chick2').removeClass('col-xs-6');
                        $('#chick2').addClass('col-xs-8');
                        $('#chick2').addClass('col-xs-offset-2');
                    } else {
                        $('#chick1').show();
                        $('#chick2').addClass('col-xs-6');
                        $('#chick2').removeClass('col-xs-8');
                        $('#chick2').removeClass('col-xs-offset-2');
                    }
                }
                if (newRound.chick2 != null) {
                    if (currUser.id == newRound.chick2.id) {
                        $('#chick2').hide();
                        $('#chick1').removeClass('col-xs-6');
                        $('#chick1').addClass('col-xs-8');
                        $('#chick1').addClass('col-xs-offset-2');
                    } else {
                        $('#chick2').show();
                        $('#chick1').addClass('col-xs-6');
                        $('#chick1').removeClass('col-xs-8');
                        $('#chick1').removeClass('col-xs-offset-2');
                    }
                }



                if (newRound.chick1 == null || newRound.chick1.id != currUser.id) isChick1Stream = false;
                if (newRound.chick2 == null || newRound.chick2.id != currUser.id) isChick2Stream = false;
                // end YOUR stream if you are not a chick
                if (newRound.chick1 != null && newRound.chick1.id != currUser.id &&
                    newRound.chick2 != null && newRound.chick2.id != currUser.id) {
                    endStream(currUserStreamCtrl);
                }

                // end other streams if they have changed
                if (newRound.chick1 != null && newRound.chick1.email != chick1StreamEmail) {
                    endStream(chick1StreamCtrl);
                }
                if (newRound.chick2 != null && newRound.chick2.email != chick2StreamEmail) {
                    endStream(chick2StreamCtrl);
                }



                currRound = newRound;


                if (newRound.chick1 != null && newRound.chick2 != null &&
                    newRound.chick1.id != currUser.id && newRound.chick2.id != currUser.id) {
                    confirmed = false;
                }

                if (newRound.started) {
                    console.log('round is started!!!!!!!!');
                    attachSpotlight();

                } else if (!newRound.started) {
                    alreadyVoted = false;

                    if (!confirmed && newRound.chick1 != null && newRound.chick1.id == currUser.id && !newRound.chick1Ready) {
                        // var willing = confirm('are you willing 1?');
                        var willing = true;
                        if (willing) {

                            if (!isChick1Stream) {
                                isChick1Stream = true;
                                stream(currUser.email, function (ctrl) {
                                    $('#chick1StreamContainer video').remove();
                                    setTimeout(function() {
                                        setChick1Ready(function (res) { console.log('Set chick1 ready'); });
                                    }, setReadyDelay);
                                });
                            } else {
                                setTimeout(function() {
                                    setChick1Ready(function (res) { console.log('Set chick1 ready'); });
                                }, setReadyDelay);
                            }

                        } else {
                            // setChick1Drop(function(res) { console.log('c1 NOT willing')});
                        }
                    }

                    if (!confirmed && newRound.chick2 != null && newRound.chick2.id == currUser.id && !newRound.chick2Ready) {
                        // var willing = confirm('are you willing 2?');
                        var willing = true;
                        if (willing) {

                            if (!isChick2Stream) {
                                isChick2Stream = true;
                                stream(currUser.email, function (ctrl) {
                                    $('#chick2StreamContainer video').remove();
                                    setTimeout(function() {
                                        setChick2Ready(function (res) { console.log('Set chick2 ready'); });
                                    }, setReadyDelay);
                                });
                            } else {
                                setTimeout(function() {
                                    setChick2Ready(function (res) { console.log('Set chick2 ready'); });
                                }, setReadyDelay);
                            }

                        } else {
                            // setChick2Drop(function(res) { console.log('c2 NOT willing')});
                        }
                    }

                }


                $scope.currentRound = newRound;
                $scope.$apply();

                refreshSpotlightDisplay();
            });
        });
    });

    // Force an initial refresh
    setTimeout(function () {
        getCurrentRound(function (res) {
            $scope.currentRound = res;
            currRound = $scope.currentRound;
            $scope.$apply();

            refreshSpotlightDisplay();
        });
    }, setReadyDelay);


});


var theUser;
$(document).ready(function () {

    // Handle user playing/not playing
    $('#qAble').change(function() {
        // var willingToQueue = !($(this).parent().hasClass('off'));
        var willingToQueue = $(this).is(":checked");

        console.log('Willing to queue: ' + willingToQueue);

        if (willingToQueue) {
            addSelfToQueue(function(res) { console.log('Added self to queue'); });
        } else {
            removeSelfFromQueue(function(res) { console.log('Removed self from queue'); });
            endStream(currUserStreamCtrl);
        }
    });



    refreshSpotlightDisplay();

    //init chat
    getSelf(function(res){
        theUser = res;
        initChat();

        if(res.status.name == "permanent ban") {
            window.location.replace("logoutUser");
        }
    });
    setInterval(spamFilter, 2000);


    var navHeight = $('nav').outerHeight();
    var docHeight = $(document).outerHeight();
    var bodyPadding = parseInt($('body').css('padding-top'));
    var globalChatContainerHeight = docHeight - navHeight;
    $('#globalChatContainer').css('height', globalChatContainerHeight);
    $('#globalChatContainer').css('margin-top', navHeight);


    var messageContainerHeight = $('#messageContainer').outerHeight();
    var chatboxHeight = globalChatContainerHeight - messageContainerHeight;
    $('#chatbox').css('height', chatboxHeight);


    $('#voteChick1').click(function () {
        if (!alreadyVoted) {
            alreadyVoted = true;
            voteChick1(function (res) { });
        }
    });

    $('#voteChick2').click(function () {
        if (!alreadyVoted) {
            alreadyVoted = true;
            voteChick2(function (res) { });
        }
    });


    chick1StreamContainer = document.getElementById('chick1StreamContainer');
    chick2StreamContainer = document.getElementById('chick2StreamContainer');
});

function stream(number, callback) {
    endStream(currUserStreamCtrl);

    var phone = window.phone = PHONE({
        number: number, // listen on username else random
        publish_key: WEBRTC_PUB_KEY,
        subscribe_key: WEBRTC_SUB_KEY,
        ssl: true,
        oneway: true,	// One-Way streaming enabled
        broadcast: true	// True since you are the broadcaster
    });

    phone.unable(function(details) { console.log('FAILED TO STREEEEEAM'); });
    phone.disconnect(function(){ console.log('network GONE.') });
    phone.reconnect(function(){  console.log('network BACK!') });


    currUserStreamCtrl = CONTROLLER(phone);

    currUserStreamCtrl.ready(function () {
        currUserStreamCtrl.stream(); 	// Begin streaming video
        callback(currUserStreamCtrl);
    });

    currUserStreamCtrl.unable(function() {
        console.log('UNABLE TO STReeeeeAAAM');
    });

}

function getStream(ctrl, number, callback) {
    endStream(ctrl);

    var phone = window.phone = PHONE({
        number: "Viewer" + Math.floor(Math.random() * 10000), // Random name
        publish_key: WEBRTC_PUB_KEY,
        subscribe_key: WEBRTC_SUB_KEY,
        ssl: true,
        oneway: true	// One way streaming enabled
    });

    phone.unable(function(details) { console.log('UNABLE TO CONEEEECCCT'); });
    phone.disconnect(function(){ console.log('network GONE.') });
    phone.reconnect(function(){  console.log('network BACK!') });


    ctrl = CONTROLLER(phone, true);

    ctrl.ready(function () {
        ctrl.joinStream(number);
    });

    ctrl.receive(function (session) {
        session.connected(function (session) {
            callback(session.video);
        });
    });


}

function endStream(ctrl) {
    try {
        ctrl.hangup();
    } catch (e) {
        console.log('Failed to end stream');
    }
}

function attachSpotlight() {
    var c1HasVid = $('#chick1StreamContainer video').length > 0;
    var c2HasVid = $('#chick2StreamContainer video').length > 0;

    if (!isChick1Stream && currRound != null && currRound.chick1 != null && currRound.chick1Ready && (currRound.chick1.email != chick1StreamEmail || !c1HasVid)) {
        chick1StreamEmail = currRound.chick1.email;
        endStream(chick1StreamCtrl);
        getStream(chick1StreamCtrl, currRound.chick1.email, function (video) {
            $('#chick1StreamContainer video').remove();
            chick1StreamContainer.appendChild(video);
        });
    }

    if (!isChick2Stream && currRound != null && currRound.chick2 != null && currRound.chick2Ready && (currRound.chick2.email != chick2StreamEmail || !c2HasVid)) {
        chick2StreamEmail = currRound.chick2.email;
        endStream(chick2StreamCtrl);
        getStream(chick2StreamCtrl, currRound.chick2.email, function (video) {
            $('#chick2StreamContainer video').remove();
            chick2StreamContainer.appendChild(video);
        });
    }
}

