var app = angular.module('mainApp', []);

function getUser(userId, callback) {
    $.ajax({
        url: "users/" + userId,
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function getChick1(callback) {
    $.ajax({
        url: "spotlight/getChick1",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, status, xhr) {
            callback(null);
        }
    });
}

function getChick2(callback) {
    $.ajax({
        url: "spotlight/getChick2",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        },
        error: function (res, status, xhr) {
            callback(null);
        }
    });
}

function getSelf(callback) {
    $.ajax({
        url: "users/getSelf",
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

function voteChick1(callback) {
    $.ajax({
        url: "spotlight/voteChick1",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function voteChick2(callback) {
    $.ajax({
        url: "spotlight/voteChick2",
        method: "GET",
        success: function (res, status, xhr) {
            callback(res);
        }
    });
}

function getCurrentRound(callback) {
    $.ajax({
        url: "spotlight/getCurrentRound",
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
