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




<!-- Navigation bar/menu-->
<nav>
    <a href="/landing">landing</a>
    <a href="/home">home</a>
    <a href="/profile">profile</a>
</nav>

<div id="spotlight">
    <div id="chick1StreamContainer"></div>
    <div id="chick2StreamContainer"></div>

    <button id="chick1StreamBtn">chick1-stream</button>
    <button id="chick2StreamBtn">chick2-stream</button>

    <button onclick="attachSpotlight();">View</button>
</div>

<div id="global">
    <p>global chat</p>
</div>

<div id="misc">
    <p>misc info</p>
</div>

<!-- "Footer" container-->
<footer>
</footer>


</body>
</html>
