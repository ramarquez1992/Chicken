$(document).ready(function () {
    console.log("hello, leaderboard");
    
    console.log("most games");
    getMostGames(function(res) {
    	var mostGames = $('#mostGames').html();
    	$('#mostGames').html(mostGames + " " + res) ;
        console.log(res);
        
    });
    
    console.log("most wins");
    getMostGamesWon(function(res) {
    	var mostWins = $('#mostWins').html();
    	$('#mostWins').html(mostWins + " " + res);
    	console.log(res);
    });
});
