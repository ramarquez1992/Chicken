$(document).ready(function () {
    console.log("hello, home");
    // getSpotlightQueue();
    // getUser(1);

    video_out  = document.getElementById("vid-box");
    embed_code = document.getElementById("embed-code");
    here_now   = document.getElementById('here-now');
    streamName = null;
});

function stream(form) {
    streamName = form.streamname.value || Math.floor(Math.random()*100)+''; // Random stream if not provided
    var phone = window.phone = PHONE({
        number        : streamName, // listen on username else random
        publish_key   : WEBRTC_PUB_KEY,
        subscribe_key : WEBRTC_SUB_KEY,
        oneway        : true,	// One-Way streaming enabled
        broadcast     : true	// True since you are the broadcaster
    });
    var ctrl = window.ctrl = CONTROLLER(phone);
    ctrl.ready(function(){
        form.streamname.style.background="#55ff5b";
        form.stream_submit.hidden="true";
        ctrl.addLocalStream(video_out);
        ctrl.stream(); 	// Begin streaming video
    });
    ctrl.streamPresence(function(m){ here_now.innerHTML=m.occupancy; });
    return false;  // So form does not submit
}

function watch(form){
    console.log(form);

    var num = form.number.value;  // Stream to join
    var phone = window.phone = PHONE({
        number        : "Viewer" + Math.floor(Math.random()*100), // Random name
        publish_key   : WEBRTC_PUB_KEY,
        subscribe_key : WEBRTC_SUB_KEY,
        oneway        : true	// One way streaming enabled
    });

    var ctrl = window.ctrl = CONTROLLER(phone, true);
    ctrl.ready(function(){
        ctrl.joinStream(num);
        // ctrl.isStreaming(num, function(isOn){
        //     ctrl.joinStream(num);
            // if (isOn) ctrl.joinStream(num);
            // else alert("User is not streaming!");
        // });
    });
    ctrl.receive(function(session){
        session.connected(function(session){ video_out.appendChild(session.video); });
    });
    ctrl.streamPresence(function(m){ here_now.innerHTML=m.occupancy; });

    return false;  // Prevent form from submitting
}



function end(){
    ctrl.hangup();
}

function mute(){
    var audio = ctrl.toggleAudio();
    if (!audio) $("#mute").html("Unmute");
    else $("#mute").html("Mute");
}

function pause(){
    var video = ctrl.toggleVideo();
    if (!video) $('#pause').html('Unpause');
    else $('#pause').html('Pause');
}