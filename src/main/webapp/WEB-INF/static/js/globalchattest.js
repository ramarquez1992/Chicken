var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

var theUser;
var uStatus;
$(document).ready(function(){
	
	getSelf(function(res){
		theUser = res;
	});
	
	initChat();
});

function initChat() {
	var userName = document.getElementById('firstName').innerHTML + " " + document.getElementById('lastName').innerHTML;
	var num = document.getElementById('idNum').innerHTML
	uStatus = document.getElementById('status').innerHTML
	
	var games = 0;
	
	skylink.setUserData({
		name: userName,
		userId : num,
		status : uStatus
	});
	
	skylink.joinRoom();
}

var userList = [];

skylink.on('peerJoined', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	var id = "";
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
		id = peerInfo.userData.userId || peerId;
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

skylink.on('incomingMessage', function(message, peerId, peerInfo, isSelf) {
	var user = 'You',
	className = 'you';
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
		if(!userList.includes(user.name) && user.userId != document.getElementById('idNum').innerHTML) {
			var userListBox = document.getElementById('userList');
			var userModel = document.createElement('li');
			userModel.className = 'message';
			userModel.style.cssText = "color:orange;"
			userModel.textContent = user.name;
			userModel.onclick = updateModal(user);
			userListBox.appendChild(userModel);
			userList.push(user.name);
		}
	
		var userProfile = document.createElement('span');
		var userMessage = document.createElement('span');
		userProfile.className = className;
		userProfile.style.cssText = "color:purple;"
		userProfile.textContent = user.name;
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
			$("#fullName").text("Name: " + res.firstName + " " + res.lastName);
			$("#selectStatus").val(res.status.name);
			console.log("inside " + res.status.name);
			if(uStatus != "admin" && uStatus != "Chicken") {
				$("#selectStatus").prop("disabled", true);
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
			console.log("inside " + res.id);
			
			$('#statusButton').click(updateUserStatus(res.id));
			
			
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

function updateUserStatus(id) {
	return function() {
		console.log("button clicked");
		var userId = id
		console.log("out user id " + userId);
		var newStatus = $('#selectStatus').find(':selected').text();
		console.log("out select status " + newStatus);
		updateUserAjax(userId, newStatus, function(res) {
			console.log(res);
		});
	}
}
