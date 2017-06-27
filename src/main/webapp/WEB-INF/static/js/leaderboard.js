$(document).ready(function () {
    console.log("hello, leaderboard");
    
    getMostGames(function(res) {
    	var mostGames = $('#mostGames').html();
    	$('#mostGames').html(mostGames + " " + res) ;
        console.log(res);
        
    });
    
    getMostGamesWon(function(res) {
    	var mostWins = $('#mostWins').html();
    	$('#mostWins').html(mostWins + " " + res);
    	console.log(res);
    });
    
    getMostSpotlightTime(function(res) {
    	var mostTime = $('#mostTime').html();
    	$('#mostTime').html(mostTime + " " + res);
    	console.log(res);
    })
});
