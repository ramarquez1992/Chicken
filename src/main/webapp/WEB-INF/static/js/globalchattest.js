var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

window.onload = setTimeout(initChat, 250);

function initChat() {
	var userName = document.getElementById('firstName').innerHTML + " " + document.getElementById('lastName').innerHTML;
	var num = document.getElementById('idNum').innerHTML
	
	skylink.setUserData({
		name: userName,
		userId : num
	});
	
	skylink.joinRoom();
}

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
//	var input = document.getElementById('message');
//	var isBaby = document.getElementById('isBaby').innerHTML;
//	var censorInput = input.value;
//	alert(isBaby);
//	if(isBaby=='true') censorInput = replaceWords(input);
//	skylink.sendP2PMessage(censorInput);
//	input.value = '';
//	input.select();
	
	var input = document.getElementById('message');
	skylink.sendP2PMessage(input.value);
	input.value = '';
	input.select();
}

function addMessage(user, message, className) {
	var chatbox = document.getElementById('chatbox');
	var div = document.createElement('div');
	
	var isBaby = document.getElementById('isBaby').innerHTML;
	var censorInput = message;
	
	if(isBaby=='true') {
		alert("Message = " + message);
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
		userProfile.onclick = function(){
			$('#UserProfile').modal({
		  		backdrop: 'static'
			}); 
			 $("#fullName").text(user.name);
			 $("#something").text(user.userId);
		};
		userMessage.textContent = censorInput;
		div.appendChild(userProfile);
		div.appendChild(userMessage);
	}
	chatbox.appendChild(div);
}

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
    var censored = censor(message, badWords);
    return censored;
}

function censor(string, filters) {
	
    var regex = new RegExp(filters.join("\\b|\\b"), "gi");
    return string.replace(regex, function (match) {
        var stars = '';
        for (var i = 0; i < match.length; i++) {
            stars += '*';
        }
        return stars;
    });
}