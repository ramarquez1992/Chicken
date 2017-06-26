var skylink = new Skylink();

skylink.init({
	appKey: '0d7c624d-c264-4d1b-8e19-69de8680bb2d',
	defaultRoom: 'LGpMxj'
});

skylink.on('peerJoined', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
	}
	addMessage(user + ' joined the room', 'action');
});

skylink.on('peerUpdated', function(peerId, peerInfo, isSelf) {
	if(isSelf) {
		user = peerInfo.userData.name || peerId;
		addMessage('You\'re now known as ' + user, 'action');
	}
});

skylink.on('peerLeft', function(peerId, peerInfo, isSelf) {
	var user = 'You';
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
	}
	addMessage(user + ' left the room', 'action');
});

skylink.on('incomingMessage', function(message, peerId, peerInfo, isSelf) {
	var user = 'You',
	className = 'you';
	if(!isSelf) {
		user = peerInfo.userData.name || peerId;
		className = 'message';
	}
	addMessage(user + ': ' + message.content, className);
});

function setName() {
	var input = document.getElementById('name');
	skylink.setUserData({
		name: input.value
	});
}

function joinRoom() {
	window.alert("hello!");
	skylink.joinRoom();
}

function leaveRoom() {
	skylink.leaveRoom();
}

function sendMessage() {
	var input = document.getElementById('message');
	var censoredInput = replaceWords(input);
	skylink.sendP2PMessage(censoredInput);
	input.value = '';
	input.select();
}

function addMessage(message, className) {
	var chatbox = document.getElementById('chatbox'),
	div = document.createElement('div');
	div.className = className;
	div.textContent = message;
	chatbox.appendChild(div);
}

function replaceWords(message) {
    var comment = message.value;
    var badWords = ["fuck", "shit", "crap" ,"damn"];
    var censored = censor(comment, badWords);
    comment.value = censored;
    
    return censored;
}

function censor(string, filters) {
    var regex = new RegExp(filters.join("|"), "gi");
    return string.replace(regex, function (match) {
        var stars = '';
        for (var i = 0; i < match.length; i++) {
            stars += '*';
        }
        return stars;
    });
}