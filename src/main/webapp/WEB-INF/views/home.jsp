<!DOCTYPE html>
<html>
<head>
    <title>Home &middot; #Chicken</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">

    <link rel="icon" href="/static/img/chicken.ico">
    <link rel="stylesheet" type="text/css" href="/static/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/static/lib/Poppins.font">
    <link rel="stylesheet" type="text/css" href="/static/lib/Montserrat.font">
    <link rel="stylesheet" type="text/css" href="/static/css/main.css">

    <script src="/static/lib/jquery-3.2.1.min.js"></script>
    <script src="/static/lib/bootstrap.min.js"></script>

    <script src="https://cdn.pubnub.com/pubnub-3.7.14.min.js"></script>
    <script src="https://cdn.pubnub.com/webrtc/webrtc.js"></script>
    <script src="https://cdn.pubnub.com/webrtc/rtc-controller.js"></script>

    <script src="/static/js/main.js"></script>
    <script src="/static/js/webrtcKeys.js"></script>
    <script src="/static/js/home.js"></script>


</head>
<body>

<div id="vid-box"><!-- Stream goes here --></div>

<form name="streamForm" id="stream" action="#" onsubmit="return stream(this);">
    <input type="text" name="streamname" id="streamname" placeholder="Pick a stream name!" />
    <input type="submit" name="stream_submit" value="Stream">
    <div id="stream-info">Watching: <span id="here-now">0</span></div>
</form>

<form name="watchForm" id="watch" action="#" onsubmit="return watch(this);">
    <input type="text" name="number" placeholder="Enter stream to join!" />
    <input type="submit" value="Watch"/>
</form>

<div id="inStream">
    <button id="end" onclick="end()">Done</button> <br>
</div>


</body>
</html>
