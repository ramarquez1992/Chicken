function getUser(userId, callback) {
    $.ajax({
        url: "users/" + userId,
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function getSpotlightQueue(callback) {
    $.ajax({
        url: "spotlight/getQueue",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function getMostGames(num, callback) {
	$.ajax({
		url: "leaderboard/mostGamesPlayed/" + num,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	});
}

function getMostGamesWon(num, callback) {
	$.ajax({
		url: "leaderboard/mostGamesWon/" + num,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	});
}


function gamesPlayed(userId, callback) {
	$.ajax({
		url: "leaderboard/gamesPlayed/" + userId,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	});
}

function gamesWon(userId, callback) {
	$.ajax({
		url: "leaderboard/gamesWon/" + userId,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}

function gamesLost(userId, callback) {
	$.ajax({
		url: "leaderboard/gamesLost/" + userId,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}

function spotlightTime(userId, callback) {
	$.ajax({
		url: "leaderboard/spotlightTime/" + userId,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}

function getMostSpotlightTime(num, callback) {
	$.ajax({
		url: "spotlight/mostSpotlightTime/" + num,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}

function getMostVotes(num, callback) {
	$.ajax({
		url: "leaderboard/mostVotes/" + num,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}

function totalVotes(userId, callback) {
	$.ajax({
		url: "leaderboard/totalVotes/" + userId,
		method: "GET",
		success: function (res, status, xhr) {
			callback(res);
		}
	})
}



function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';

    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
}
