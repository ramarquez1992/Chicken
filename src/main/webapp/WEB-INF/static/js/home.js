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

app.controller('SpotlightController', function ($scope) {


    getSelf(function (cu) {
        currUser = cu;
        $('#qAble').change(function() {
            console.log($(this).val() + ' qqqqqq');
        });

        // stream(currUser.email, function (ctrl) {
            // ctrl.addLocalStream(document.getElementById('myStreamContainer'));
            // $('#chick2StreamContainer video').remove();
        // });

        var socket = new WebSocket('ws://' + window.location.hostname + ':8443/sock');
        var stompClient = Stomp.over(socket);

        stompClient.connect({email: cu.email}, function (frame) {
            stompClient.subscribe('/topic/messages', function (res) {
                var newRound = JSON.parse(res.body);

                // if (newRound.chick1 != null) {
                //     if ( currUser.id == newRound.chick1.id) {
                //         $('#chick1').hide();
                //     } else {
                //         $('#chick1').show();
                //     }
                // }
                // if (newRound.chick2 != null) {
                //     if (currUser.id == newRound.chick2.id) {
                //         $('#chick2').hide();
                //     } else {
                //         $('#chick2').show();
                //     }
                // }

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
                        // confirmed = true;
                        // var willing = confirm('are you willing 1?');
                        var willing = true;
                        if (willing) {
                            // refreshStreams();
                            // setChick1Ready(function(res) { console.log('c1 willing')});

                            if (!isChick1Stream) {
                                isChick1Stream = true;
                                stream(currUser.email, function (ctrl) {
                                    $('#chick1StreamContainer video').remove();
                                    setTimeout(function() {
                                        setChick1Ready(function (res) { console.log('set chick1 ready'); });
                                    }, 2000);
                                });
                            } else {
                                setTimeout(function() {
                                    setChick1Ready(function (res) { console.log('set chick1 ready'); });
                                }, 2000);
                            }

                        } else {
                            // setChick1Drop(function(res) { console.log('c1 NOT willing')});
                        }
                    }

                    if (!confirmed && newRound.chick2 != null && newRound.chick2.id == currUser.id && !newRound.chick2Ready) {
                        // confirmed = true;
                        // var willing = confirm('are you willing 2?');
                        var willing = true;
                        if (willing) {
                            // refreshStreams();
                            // setChick2Ready(function (res) { console.log('c2 willing')});

                            if (!isChick2Stream) {
                                isChick2Stream = true;
                                stream(currUser.email, function (ctrl) {
                                    $('#chick2StreamContainer video').remove();
                                    setTimeout(function() {
                                        setChick2Ready(function (res) { console.log('set chick2 ready'); });
                                    }, 2000);
                                });
                            } else {
                                setTimeout(function() {
                                    setChick2Ready(function (res) { console.log('set chick2 ready'); });
                                }, 2000);
                            }

                        } else {
                            // setChick2Drop(function(res) { console.log('c2 NOT willing')});
                        }
                    }

                }

                // refreshStreams(newRound);

                $scope.currentRound = newRound;
                // currRound = $scope.currentRound;
                $scope.$apply();
            });
        });
    });

    // Force an initial refresh
    setTimeout(function () {
        getCurrentRound(function (res) {
            $scope.currentRound = res;
            currRound = $scope.currentRound;
            $scope.$apply();
        });
    }, 2000);


});


$(document).ready(function () {

    // init chat
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

    currUserStreamCtrl = CONTROLLER(phone);

    currUserStreamCtrl.ready(function () {
        currUserStreamCtrl.stream(); 	// Begin streaming video
        callback(currUserStreamCtrl);
    });
}

function getStream(ctrl, number, callback) {
    endStream(ctrl);

    var phone = window.phone = PHONE({
        number: "Viewer" + Math.floor(Math.random() * 1000), // Random name
        publish_key: WEBRTC_PUB_KEY,
        subscribe_key: WEBRTC_SUB_KEY,
        ssl: true,
        oneway: true	// One way streaming enabled
    });

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

    // if (currRound != null && currRoundId == currRound.id) return null;

    // currRoundId = currRound.id;
    // if (currRound != null && (!currRound.chick1Ready || !currRound.chick2Ready)) return;

    // if (isChick1Stream) $('#chick1StreamContainer video').remove();
    // if (isChick2Stream) $('#chick2StreamContainer video').remove();

    // if (!currRound != null && currRound.chick1 != null && currRound.chick1.id != currUser.id && currRound.chick1Ready && currRound.chick1.email != chick1StreamEmail) {
    if (!isChick1Stream && currRound != null && currRound.chick1 != null && currRound.chick1Ready && (currRound.chick1.email != chick1StreamEmail || !c1HasVid)) {
    // if (!isChick1Stream && currRound != null && currRound.chick1 != null && currRound.chick1Ready) {
        chick1StreamEmail = currRound.chick1.email;
        console.log('ATTEMpting to conect to chick1');
        endStream(chick1StreamCtrl);
        getStream(chick1StreamCtrl, currRound.chick1.email, function (video) {
            $('#chick1StreamContainer video').remove();
            chick1StreamContainer.appendChild(video);
        });
    }

    // if (currRound != null && currRound.chick2 != null && currRound.chick2.id != currUser.id && currRound.chick2Ready && currRound.chick2.email != chick2StreamEmail) {
    if (!isChick2Stream && currRound != null && currRound.chick2 != null && currRound.chick2Ready && (currRound.chick2.email != chick2StreamEmail || !c2HasVid)) {
    // if (!isChick2Stream && currRound != null && currRound.chick2 != null && currRound.chick2Ready) {
    // if (!isChick2Stream && currRound != null && $('#chick2StreamContainer').attr('data-number') != currRound.chick2.email) {
        chick2StreamEmail = currRound.chick2.email;
        console.log('ATTEMpting to conect to chick2');
        endStream(chick2StreamCtrl);
        getStream(chick2StreamCtrl, currRound.chick2.email, function (video) {
            $('#chick2StreamContainer video').remove();
            chick2StreamContainer.appendChild(video);
        });
    }
}


