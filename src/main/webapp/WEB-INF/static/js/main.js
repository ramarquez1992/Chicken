function getUser(userId) {
    $.ajax({
        url: "/users/" + userId,
        method: "GET",
        success: function (res, status, xhr) {
            console.log(res);
        }
    });
}

function getSpotlightQueue() {
    $.ajax({
        url: "/spotlight/getQueue",
        method: "GET",
        success: function (res, status, xhr) {
            console.log(res);
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
