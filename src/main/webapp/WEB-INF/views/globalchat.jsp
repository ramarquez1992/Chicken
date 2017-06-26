<!DOCTYPE html>
<html>
<head>
    <title>#Chicken</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

    <link rel="icon" href="static/img/chicken.ico">
    <link rel="stylesheet" type="text/css" href="static/lib/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="static/lib/Poppins.font">
    <link rel="stylesheet" type="text/css" href="static/lib/Montserrat.font">
    <link rel="stylesheet" type="text/css" href="static/css/main.css">

    <script src="static/lib/jquery-3.2.1.min.js"></script>
    <script src="static/lib/bootstrap.min.js"></script>
    <script src="static/js/main.js"></script>
    
	<script src="//cdn.temasys.io/skylink/skylinkjs/0.6.x/skylink.complete.min.js"></script>
    <script src="static/js/globalchattest.js"></script>
    
<style>
header {
  background: #eee;
  padding: 20px;
  margin-bottom: .4em;
  font-family: Helvetica, Arial, sans-serif;
}

header a:first-child {
  float: right;
  margin: 0 0 20px 30px;
}

#container {
  position: relative;
  border: 1px #ddd solid;
  height: 180px;
  overflow-y: auto;
}

#chatbox {
  position: absolute;
  bottom: 0px;
}

.action {
  font-style: italic;
  color: gray;
}

.you {
  font-weight: bold;
}

</style>    
    
</head>
<body>

  <header>
    <a href="http://skylink.io/web">More about SkylinkJS</a>
    <input type="text" id="name" placeholder="My name" autofocus />
    <button onclick="setName()">Set my name</button>
    <button onclick="joinRoom()">Join room</button>
    <button onclick="leaveRoom()">Leave room</button>
    <br/>
    <input type="text" id="message" placeholder="My message" />
    <button onclick="sendMessage()">Send message</button>
    <a href="https://codepen.io/temasys/pen/LGpMxj" target="_blank">Chat with self!</a>
  </header>
  
  <div id="container">
    <div id="chatbox"></div>
  </div>
  
</body>
</html>