var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

var theUser;
$(document).ready(function(){
	
	getSelf(function(res){
		theUser = res;
		initChat();
		if(res.status.name == "permanent ban") {
			window.location.replace("logoutUser");
		}
	});
});

var uStatus
function initChat() {
	var userName = theUser.firstName + " " + theUser.lastName;
	var num = theUser.id;
	uStatus = theUser.status.name;
	
	var games = 0;
	
	skylink.setUserData({
		name: userName,
		userId : num,
		status : uStatus
	});
	if(uStatus != "permanent ban") skylink.joinRoom();
	else console.log(uStatus + " :D");
}

var userList = [];

skylink.on('peerJoined', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	var id = "";
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
		id = peerInfo.userData.userId || peerId;
		
		if(!userList.includes(user.name) && peerInfo.userData.userId != document.getElementById('idNum').innerHTML) {
			console.log("inside add message " + peerInfo.userData.name);
			console.log("inside add message " + peerInfo.userData.userId);
			console.log("inside add message idNum " + document.getElementById('idNum').innerHTML);
			var userListBox = document.getElementById('userList');
			console.log("created user list ");
			var userModel = document.createElement('li');
			console.log("created list elements");
			userModel.className = 'message';
			userModel.style.cssText = "color:orange;"
			userModel.textContent = peerInfo.userData.name;
			console.log("username in user list " + peerInfo.userData.name);
			userModel.onclick = updateModal(peerInfo.userData);
			userListBox.appendChild(userModel);
			userList.push(peerInfo.userData.name);
		}
		
	}
	addMessage(peerInfo.userData, ' joined the room', 'action');
});

skylink.on('peerLeft', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	var id = "";
	
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
		id = peerInfo.userData.userId || peerId;
		
		var node = document.getElementById('userList');

		
		
		var elements = document.getElementsByTagName('li')
		for (var i = 0; i < elements.length; i++) {
		     if (elements[i].innerHTML.indexOf(user) !== -1) {
		         userList.pop(user);
		    	 node.removeChild(elements[i]);
		         break;
		     }
		}
		
		
		
	}
	addMessage(peerInfo.userData, ' left the room', 'action');
});
		// Play with the peerId here, getting the user's name as "You" whether it is your id or not.  
skylink.on('incomingMessage', function(message, peerId, peerInfo, isSelf) {
	var user = 'You',
	className = 'you';
	console.log("before isSelf " + isSelf);
	if(!isSelf) {
		className = 'message';
		addMessage(peerInfo.userData, ': ' + message.content, className);
	}
	else addMessage(user, user + ': ' + message.content, className);
});

function sendMessage() {
	var input = document.getElementById('message');
	var status = document.getElementById('status').innerHTML;
	if(status == "permanent ban");
	else skylink.sendP2PMessage(input.value);
	input.value = '';
	input.select();
}

function addMessage(user, message, className) {
	if(message.includes("SDFGZ####%>><.*>I*({+){JMNSGL/4//44/4SSDD%&&_%DFSRGE%E%_E%_E-E")){
		var updatedId = message.split("|");
		console.log("updated id after split " + updatedId[1] + " - " + user);
		
		if(updatedId[1] = user.userId) location.reload();
		console.log("user with updated id page is refreshed");
		return;
	}
	
	if(user.status == "shadow ban") { // don't add the message if the user is shadow ban!
		return;
	}
	
	var chatbox = document.getElementById('chatbox');
	var div = document.createElement('div');
	
	var isBaby = document.getElementById('isBaby').innerHTML;
	var censorInput = message;
	
	if(isBaby=='true') {
		censorInput = replaceWords(message);
	}
	
	div.className = className;
	
	if(censorInput.substring(0,3) == "You") {
		div.style.cssText = 'color:blue;';
		div.textContent = censorInput;
	}
	else {
		var userProfile = document.createElement('span');
		var userMessage = document.createElement('span');
		userProfile.className = className;
		userProfile.style.cssText = "color:purple;"
		userProfile.textContent = user.name;
		console.log("else add messagew " + user.name);
		userProfile.onclick = updateModal(user);
		userMessage.textContent = censorInput;
		div.appendChild(userProfile);
		div.appendChild(userMessage);
	}
	chatbox.appendChild(div);
}

function updateModal(user){
	return function(){
		$('#UserProfile').modal({});
		getUser(user.userId, function(res) {
			console.log("update modal id " + user.userId);
			$("#fullName").text("Name: " + res.firstName + " " + res.lastName);
			console.log("update modal full name " + res.firstName + " " + res.lastName);
			$("#selectStatus").val(res.status.name);
			if(uStatus != "admin" && uStatus != "Chicken") {
				$("#selectStatus").prop("disabled", true);
				$("#statusButton").hide();
			}
		    $("#votesCast").text("Total Votes Cast: " + res.votesCast);
		    $("#avatar").attr("src", res.avatar);
		    
		    gamesPlayed(res.id, function(res) {
				$("#games").text("Total Games Played: " + res);
			});	 
				 
			gamesWon(res.id, function(res) {
			    $("#wins").text("Total Wins: " + res);
			});
			
			spotlightTime(res.id, function(res) {
			    $("#spotlight").text("Time in the Spotlight: " + res);
			});
			
			totalVotes(res.id, function(res) {
			    $("#votes").text("Total Votes Recieved: " + res);
			});
			
			$('#statusChangeId').text(res.id);
			console.log("ID FROM HIDDEN: " + res.id);
			
			$('#statusButton').click(updateUserStatus);
			console.log("in update modal.");
			
			//$('#statusChangeId').text(document.getElementById("idNum").innerHTML);
			//document.getElementById("statusChangeId").innerHTML = document.getElementById("idNum").innerhtml;
			
		});	
	};
};

var badWords;
$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "static/lib/terms-to-block.csv",
        async: false,
        dataType: "text",
        success: function(data) {badWords = processData(data);}
     });
});

function processData(allText) {
    var allBadWords = allText.split(/\r\n|\n/);
    return allBadWords;
}

function replaceWords(message) {	
	var regex = new RegExp(badWords.join("\\b|\\b"), "gi");
    return message.replace(regex, function (match) {
        var stars = '';
        for (var i = 0; i < match.length; i++) {
            stars += '*';
        }
        return stars;
    });
}

function updateUserStatus() {
		var id = document.getElementById("statusChangeId").innerHTML;
		console.log("user to update id " + id);
		var newStatus = $('#selectStatus').find(':selected').text();
		console.log("the status to update " + newStatus)
		updateUserAjax(id, newStatus, function(res) {
			skylink.sendP2PMessage("|" + id + "|SDFGZ####%>><.*>I*({+){JMNSGL/4//44/4SSDD%&&_%DFSRGE%E%_E%_E-E");
		});
}
