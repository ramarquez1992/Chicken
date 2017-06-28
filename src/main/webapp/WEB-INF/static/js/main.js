function getUser(userId, callback) {
    $.ajax({
        url: "users/" + userId,
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function startNextRound(callback) {
    $.ajax({
        url: "spotlight/startNextRound",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function stopRound(callback) {
    $.ajax({
        url: "spotlight/stopRound",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function addSelfToQueue(callback) {
    $.ajax({
        url: "spotlight/addSelfToQueue",
        method: "POST",
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

function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';

    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
}
