var currUser = {};
var currRound = null;
var isChick1Stream = false;
var isChick2Stream = false;
var currUserStreamCtrl = {};
var chick1StreamCtrl = {};
var chick2StreamCtrl = {};

app.controller('SpotlightController', function ($scope) {


    getSelf(function (cu) {
        currUser = cu;

        // stream(currUser.email, function (ctrl) {
            // ctrl.addLocalStream(document.getElementById('myStreamContainer'));
            // $('#chick2StreamContainer video').remove();
        // });

        var socket = new WebSocket('ws://' + window.location.hostname + ':8443/sock');
        var stompClient = Stomp.over(socket);

        stompClient.connect({email: cu.email}, function (frame) {
            stompClient.subscribe('/topic/messages', function (res) {
                var newRound = JSON.parse(res.body);

                refreshStreams(newRound);

                $scope.currentRound = newRound;
                currRound = $scope.currentRound;
                $scope.$apply();
            });
        });
    });

    // Force an initial refresh
    setTimeout(function () {
        getCurrentRound(function (res) {
            var newRound = res;

            refreshStreams(newRound);

            $scope.currentRound = res;
            currRound = $scope.currentRound;
            $scope.$apply();
        });
    }, 1000);


});

function refreshStreams(newRound) {
    console.log('outside REFRESH');
    if (
        // not enough ppl to populate spotlight
    currRound == null || newRound == null ||
    newRound.chick1 == null || newRound.chick2 == null ||
    currRound.chick1 == null || currRound.chick2 == null ||

    // chicks have changed
    newRound.chick1.id !== currRound.chick1.id ||
    newRound.chick2.id !== currRound.chick2.id ||

    newRound.chick1Ready !== currRound.chick1Ready ||
    newRound.chick2Ready !== currRound.chick2Ready
    ) {

        // TODO: end ALL streams
        if (
            currUser != null && newRound != null &&
            newRound.chick1 != null && newRound.chick2 != null &&
            newRound.chick1.id != currUser.id && newRound.chick2.id != currUser.id) {
            endStream(currUserStreamCtrl);
        }
        if (
            currRound != null && newRound != null &&
            newRound.chick1 != null && currRound.chick1 != null &&
            newRound.chick1.id !== currRound.chick1.id) {
            endStream(chick1StreamCtrl);
        }
        if (
            currRound != null && newRound != null &&
            newRound.chick2 != null && currRound.chick2 != null &&
            newRound.chick2.id !== currRound.chick2.id) {
            endStream(chick2StreamCtrl);
        }

        if (
            currUser != null && newRound != null &&
            newRound.chick1 != null && newRound.chick2 != null
        ) {

            currRound = newRound;

            console.log('trying to refresh streams');


            // TODO: only stream if user not already streaming aka not a chick
            if (newRound.chick1.id === currUser.id) {

                if (!isChick1Stream) {
                    console.log('about to stream chick1');
                    stream(currUser.email, function (ctrl) {
                        console.log('streaming chick1');
                    // stream('chick1', function (ctrl) {
                        $('#chick1StreamContainer video').remove();
                        setChick1Ready(function(res) {
                            console.log('set chick1 ready');
                        });
                        // ctrl.addLocalStream(chick1StreamContainer);
                        // forceUpdate(function (res) { });
                    });
                }

                isChick1Stream = true;
                isChick2Stream = false;

                // attachSpotlight();

            } else if (newRound.chick2.id === currUser.id) {

                if (!isChick2Stream) {
                    console.log('abou to stream chick2');
                    stream(currUser.email, function (ctrl) {
                        console.log('streaming chick2');
                    // stream('chick2', function (ctrl) {
                        $('#chick2StreamContainer video').remove();
                        setChick2Ready(function(res) {
                            console.log('set chick2 ready');
                        });
                        // ctrl.addLocalStream(chick2StreamContainer);
                        // forceUpdate(function (res) { });
                    });
                }
                isChick1Stream = false;
                isChick2Stream = true;

                // attachSpotlight();

            } else {
                isChick1Stream = false;
                isChick2Stream = false;

                // attachSpotlight();
            }
            attachSpotlight();


        }

    }

}


$(document).ready(function () {


    $('#voteChick1').click(function () {
        voteChick1(function (res) {
            //
        });
    });

    $('#voteChick2').click(function () {
        voteChick2(function (res) {
            //
        });
    });


    chick1StreamContainer = document.getElementById('chick1StreamContainer');
    chick2StreamContainer = document.getElementById('chick2StreamContainer');
});

function stream(number, callback) {

    var phone = window.phone = PHONE({
        number: number, // listen on username else random
        publish_key: WEBRTC_PUB_KEY,
        subscribe_key: WEBRTC_SUB_KEY,
        oneway: true,	// One-Way streaming enabled
        broadcast: true	// True since you are the broadcaster
    });

    // var ctrl = window.ctrl = CONTROLLER(phone);
    currUserStreamCtrl = CONTROLLER(phone);

    currUserStreamCtrl.ready(function () {
        currUserStreamCtrl.stream(); 	// Begin streaming video
        callback(currUserStreamCtrl);
    });
}

function getStream(ctrl, number, callback) {
    // TODO: needed?
    // endStream(ctrl);

    var phone = window.phone = PHONE({
        number: "Viewer" + Math.floor(Math.random() * 1000), // Random name
        publish_key: WEBRTC_PUB_KEY,
        subscribe_key: WEBRTC_SUB_KEY,
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
    if (currRound != null && !currRound.chick1Ready || !currRound.chick2Ready) return;

    if (!isChick1Stream && currRound != null && currRound.chick1 != null) {
        // getStream(chick1StreamCtrl, 'chick1', function (video) {
        getStream(chick1StreamCtrl, currRound.chick1.email, function (video) {
            $('#chick1StreamContainer video').remove();
            chick1StreamContainer.appendChild(video);
        });
    }

    if (!isChick2Stream && currRound != null && currRound.chick2 != null) {
        // getStream(chick2StreamCtrl, 'chick2', function (video) {
        getStream(chick2StreamCtrl, currRound.chick2.email, function (video) {
            $('#chick2StreamContainer video').remove();
            chick2StreamContainer.appendChild(video);
        });
    }
}


