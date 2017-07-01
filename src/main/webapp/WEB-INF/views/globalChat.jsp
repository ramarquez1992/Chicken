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

/* The Modal (background) */
.modal {
	backdrop: 'static';
	display: none;
    position: fixed; /* Stay in place */
    padding-top: 50px; /* Location of the box */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.0); /* Black w/ opacity */
    data-backdrop: 'static';
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

/* The Close Button */
.close {
    color: #aaaaaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

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
  width: 70%;
  bottom: 0px;
}

#userList {
	float:left;
	margin-left:70%;
	border: 1px #ddd solid;
	width:125px;
}

.action {
  font-style: italic;
  color: gray;
}

.you {
  font-weight: bold;
  color:purple;
}

</style>    
    
</head>
<body>
<span id="firstName" style="visibility: hidden;">${user.getFirstName()}</span>
<span id="lastName" style="visibility: hidden;">${user.getLastName()}</span>
<span id="idNum" style="visibility: hidden;">${user.getId()}</span>
<span id="isBaby" style="visibility: hidden;">${user.isBaby()}</span>
<span id="status" style="visibility: hidden;">${user.getStatus().getName()}</span>
  <header>
    <br/>
    <input type="text" id="message" placeholder="My message" />
    <button onclick="sendMessage()">Send message</button>
    <a href="https://codepen.io/temasys/pen/LGpMxj" target="_blank">Chat with self!</a>
  </header>
  
  <div id="container">
    <div id="chatbox"></div>
    <div id="userList"><p>User List</p></div>
 </div>

<div class="modal fade" id="UserProfile">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">User Profile</h4>
			</div>
			<div class="modal-body">
				<h3>User Info</h3>
				<img id="avatar" src=""/>
				<p id="fullName"></p>
				<div id="userStatus">
						<select id="selectStatus">
					  		<option id ="stat1" value="normal">normal</option>
						    <option id ="stat2" value="shadow ban">shadow ban</option>
						    <option id ="stat3" value="permanent ban">permanent ban</option>
						    <option id ="stat4" value="admin" selected>admin</option>
						    <option id ="stat5" value="admin" selected>Chicken</option>
						</select>
						<button id = "statusButton">Submit Changes</button>
				</div>
				<p id="games">?</p>
				<p id="wins"></p>
				<p id="spotlight"></p>
				<p id="votes"></p>
				<p id="votesCast"></p>	
				<span id="statusChangeId" style="visibility: hidden;"></span>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

</body>
</html>