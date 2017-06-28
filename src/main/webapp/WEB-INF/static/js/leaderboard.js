$(document).ready(function () {
    console.log("hello, leaderboard");
    
    getMostGames(function(res) {
    	var mostGames = $('#mostGames').html();
    	var mostGamesUser = res;
    	$('#mostGames').html(mostGames + " " + res.firstName + " " + res.lastName) ;
        console.log(res);
        
        gamesPlayed(res.id, function(numGames) {
        	var gamesPlayed = $('#gamesPlayed').html();
        	$('#gamesPlayed').html(gamesPlayed + " " + numGames) ;
        	console.log(numGames)
        });
    });
    
    getMostGamesWon(function(res) {
    	var mostWins = $('#mostWins').html();
    	$('#mostWins').html(mostWins + " " + res.firstName + " " + res.lastName);
    	console.log(res);
    	
    });
    
    getMostSpotlightTime(function(res) {
    	var mostTime = $('#mostTime').html();
    	$('#mostTime').html(mostTime + " " + res.firstName + " " + res.lastName);
    	console.log(res);
    })
    
});
