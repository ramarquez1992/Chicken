var currUser = {};
var currRound = null;

app.controller('SpotlightController', function ($scope) {


    getSelf(function(cu) {
        currUser = cu;

        var socket = new WebSocket('ws://' + window.location.hostname + ':8443/sock');
        var stompClient = Stomp.over(socket);

        stompClient.connect({ email: cu.email }, function(frame) {
            stompClient.subscribe('/topic/messages', function(res){
                var newRound = JSON.parse(res.body);
                // console.log(newRound);

                refreshStreams(newRound);

                $scope.currentRound = newRound;
                currRound = $scope.currentRound;
                $scope.$apply();
            });
        });
    });

    // Force an initial refresh
    setTimeout(function() {
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

    if ( (currRound == null ) ||
        newRound.chick1 == null || newRound.chick2 == null ||
        currRound.chick1 == null || currRound.chick2 == null ||

        newRound.chick1.id !== currRound.chick1.id ||
        newRound.chick2.id !== currRound.chick2.id
    ) {
        try {
            endStream();
        } catch (e) {
            console.log('Did not close stream');
        }
    } else {
        // TODO: only stream if user not already streaming aka not a chick
        if (currRound.chick1.id === currUser.id || currRound.chick2.id === currUser.id) {
            stream(currUser.email, function (ctrl) {
                // TODO: only attach to opposing chick
                attachSpotlight();
            });
        } else {
            attachSpotlight();
        }
    }

}


$(document).ready(function () {

    $('#voteChick1').click(function() {
        voteChick1(function(res) {
            //
        });
    });

    $('#voteChick2').click(function() {
        voteChick2(function(res) {
            //
        });
    });


    chick1StreamContainer = document.getElementById('chick1StreamContainer');
    chick2StreamContainer = document.getElementById('chick2StreamContainer');

    $('#chick1StreamBtn').click(function () {
        // stream('chick1', function (ctrl) {
        stream(currUser.email, function (ctrl) {
            $('#chick1StreamContainer video').remove();
            ctrl.addLocalStream(chick1StreamContainer);
        });
    });

    $('#chick2StreamBtn').click(function () {
        // stream('chick2', function (ctrl) {
        stream(currUser.email, function (ctrl) {
            $('#chick2StreamContainer video').remove();
            ctrl.addLocalStream(chick2StreamContainer);
        });
    });
});

function stream(number, callback) {
    var phone = window.phone = PHONE({
        number        : number, // listen on username else random
        publish_key   : WEBRTC_PUB_KEY,
        subscribe_key : WEBRTC_SUB_KEY,
        oneway        : true,	// One-Way streaming enabled
        broadcast     : true	// True since you are the broadcaster
    });

    var ctrl = window.ctrl = CONTROLLER(phone);

    ctrl.ready(function(){
        ctrl.stream(); 	// Begin streaming video
        callback(ctrl);
    });
}

function getStream(number, callback){
    var phone = window.phone = PHONE({
        number        : "Viewer" + Math.floor(Math.random()*1000), // Random name
        publish_key   : WEBRTC_PUB_KEY,
        subscribe_key : WEBRTC_SUB_KEY,
        oneway        : true	// One way streaming enabled
    });

    var ctrl = window.ctrl = CONTROLLER(phone, true);

    ctrl.ready(function (){
        ctrl.joinStream(number);
    });

    ctrl.receive(function (session){
        session.connected(function (session){
            callback(session.video);
        });
    });
}

function endStream(){
    window.ctrl.hangup();
}

function attachSpotlight() {
    getStream(currRound.chick1.email, function (video) {
        $('#chick1StreamContainer video').remove();
        chick1StreamContainer.appendChild(video);
    });

    getStream(currRound.chick2.email, function (video) {
        $('#chick2StreamContainer video').remove();
        chick2StreamContainer.appendChild(video);
    });
}


