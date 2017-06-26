$(document).ready(function () {
    console.log("hello, landing");
    getUser(1, function (res) {
        console.log(res);
    });

    getSpotlightQueue(function(res) {
        console.log(res);
    });
});
