var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

var theUser;
var userMessageCount = 0; //amount of message sent in limit
var messageLimit = 0; //flag for spam filter

$(document).ready(function(){
	
	getSelf(function(res){
		theUser = res;
		
		console.log(theUser);
		console.log(res);
		console.log("SIGH");
		
		initChat();
		if(res.status.name == "permanent ban") {
			window.location.replace("logoutUser");
		}
	});
	
	setInterval(spamFilter, 2000);
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
}

var userList = [];

skylink.on('peerJoined', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	var id = "";
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
		id = peerInfo.userData.userId || peerId;
	}
	if(!userList.includes(user.name)) {
		var userListBox = document.getElementById('userList');
		var userModel = document.createElement('li');
		userModel.className = 'message';
		userModel.style.cssText = "color:purple;"
		if(peerInfo.userData.userId == document.getElementById('idNum').innerHTML) userModel.style.cssText = "color:blue;";
		userModel.textContent = peerInfo.userData.name;
		userModel.onclick = updateModal(peerInfo.userData);
		userListBox.appendChild(userModel);
		userList.push(peerInfo.userData.name);
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
	
	if(!isSelf) {
		className = 'message';
		addMessage(peerInfo.userData, ': ' + message.content, className);
	}
	else addMessage(user, user + ': ' + message.content, className);
});

function sendMessage() {
	var input = document.getElementById('message');
	var status = document.getElementById('status').innerHTML;
	
	if(messageLimit == 1) {
		return;
	}
	else if(userMessageCount >= 3) {
		messageLimit = 1;
		setTimeout(resetLimit, 10000);
		alert("Spam detected: please wait 10 seconds to message again.");
		return;
	}
	else {
		userMessageCount++;
	}
	
	if(status == "permanent ban");
	else skylink.sendP2PMessage(input.value);
	input.value = '';
	input.select();
}

function addMessage(user, message, className) {
	if(message.includes("SDFGZ####%>><.*>I*({+){JMNSGL/4//44/4SSDD%&&_%DFSRGE%E%_E%_E-E")){
		var updatedId = message.split("|");
		if(updatedId[1] == theUser.id) window.location.reload();
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
			if(uStatus != "admin" && uStatus != "Chicken") {
				$("#selectStatus").prop("disabled", true);
				$("#statusButton").hide();
			}
			
			if(res > 0) $("#votesCast").text("Total Votes Cast: " + res.votesCast);
		    else {
		    	$("#votesCast").text(" ");
		    }
		    
		    $("#avatar").attr("src", res.avatar);
		    
		    gamesPlayed(res.id, function(res) {
				 if(res > 0) $("#games").text("Total Games Played: " + res);
				    else {
				    	$("#games").text(" ");
				    }
			});	 
				 
			gamesWon(res.id, function(res) {
			    if(res > 0) $("#wins").text("Total Wins: " + res);
			    else {
			    	$("#wins").text(" ");
			    }
			});
			
			spotlightTime(res.id, function(res) {
				console.log(res);
				console.log(res.includes("0 hrs 0 mins 0 secs"));
			    if(!res.includes("0 hrs 0 mins 0 secs")) $("#spotlight").text("Time in the Spotlight: " + res);
			    else {
			    	$("#spotlight").text("You have no stats to display!");
			    }
			});
			
			totalVotes(res.id, function(res) {
			    if(res > 0) $("#votes").text("Total Votes Recieved: " + res);
			    else {
			    	$("#votes").text(" ");
			    }
			    console.log("res = " + res);
			});
			
			$('#statusChangeId').text(res.id);
			
			$('#statusButton').click(updateUserStatus);
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
	console.log("in update status function id " + id);
	var newStatus = $('#selectStatus').find(':selected').text();
	updateUserAjax(id, newStatus, function(res) {
		skylink.sendP2PMessage("|" + id + "|SDFGZ####%>><.*>I*({+){JMNSGL/4//44/4SSDD%&&_%DFSRGE%E%_E%_E-E");
	});
}

function spamFilter() {
	userMessageCount = 0;
}

function resetLimit() {
	messageLimit = 0;
}

function handleKeyPress(e){
	var key=e.keyCode || e.which;
	if (key==13){
		sendMessage();
	}
}
