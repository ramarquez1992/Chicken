$(document).ready(function () {
    addSelfToQueue(function(res) {
        //
    });

    $('#startNextRound').click(function() {
        startNextRound(function(res) {
            //
        });
    });

    $('#stopRound').click(function() {
        stopRound(function(res) {
            //
        });
    });

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

    getSelf(function(res) {
        currUser = res;
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

function end(){
    window.ctrl.hangup();
}

function refreshChicks(callback) {
    getChick1(function(res) {
        chick1 = res;

        getChick2(function(res2) {
            chick2 = res2;
            callback();
        });
    });
}

function attachSpotlight() {
    refreshChicks(function() {

        getStream(chick1.email, function (video) {
            $('#chick1StreamContainer video').remove();
            chick1StreamContainer.appendChild(video);
        });

        getStream(chick2.email, function (video) {
            $('#chick2StreamContainer video').remove();
            chick2StreamContainer.appendChild(video);
        });
    });
}