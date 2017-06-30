var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

window.onload = setTimeout(initChat, 250);

function initChat() {
	var userName = document.getElementById('firstName').innerHTML + " " + document.getElementById('lastName').innerHTML;
	var num = document.getElementById('idNum').innerHTML
	var uStatus = document.getElementById('status').innerHTML
	
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

//function updateModal(user){
//	$('#UserProfile').modal({}); 
//	$("#fullName").text(user.name);
//	$("#userStatus").text("Status: " + user.status);
//	gamesPlayed(user.userId, function(res) {
//		$("#games").text("Total Games Played: " + res);
//	});	 
//		 
//	gamesWon(user.userId, function(res) {
//	    $("#wins").text("Total Wins: " + res);
//	});
//	
//	spotlightTime(user.userId, function(res) {
//	    $("#spotlight").text("Time in the Spotlight: " + res);
//	});
//	
//	totalVotes(user.userId, function(res) {
//	    $("#votes").text("Total Votes Recieved: " + res);
//	});
//	
//	getUser(user.userId, function(res) {
//	    $("#votesCast").text("Total Votes Cast: " + res.votesCast);
//	    $("#avatar").attr("src", res.avatar);
//	});		
//	
//}; 

function updateModal(user){
	return function(){
		$('#UserProfile').modal({});
		getUser(user.userId, function(res) {
			$("#fullName").text("Name: " + res.firstName + " " + res.lastName);
			$("#userStatus").text("Status: " + res.status.name);
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
