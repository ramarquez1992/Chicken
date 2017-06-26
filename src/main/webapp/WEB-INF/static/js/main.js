// function login(email, password, callback) {
//     $.ajax({
//         url: "loginUser" + userId,
//         method: "POST",
//         success: function (res, status, xhr) {
//             callback(res);
//         }
//     });
// }

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

function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';

    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
}
