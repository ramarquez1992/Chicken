$(document).ready(function () {
    console.log("hello, leaderboard");
    
    getMostGames(3, function(res) {
    	
    	var mostGames = $('#mostGames').html();
    	var mostGamesUser = res;
    	$('#mostGames').html(mostGames + " " + res.firstName + " " + res.lastName) ;
        console.log(res);
        
        //TODO: Convert games won, games lost, and games played to handle a list of users instead of a single user
        gamesPlayed(res.id, function(numGames) {
        	var gamesPlayed = numGames;
        	var gamesPlayed = $('#mgGamesPlayed').html();
        	$('#mgGamesPlayed').html(gamesPlayed + " " + numGames) ;
        	console.log(numGames)
        });
        
        gamesWon(res.id, function(gamesWon) {
        	var gamesWon = gamesWon;
    		var mgGamesWon = $('#mgGamesWon').html();
    		$('#mgGamesWon').html(mgGamesWon + " " + gamesWon);
    		console.log(gamesWon)
    	});
       
        gamesLost(res.id, function(gamesLost) {
        	var gamesLost = gamesLost;
    		var mgGamesLost = $('#mgGamesLost').html();
    		$('#mgGamesLost').html(mgGamesLost + " " + gamesLost);
    		console.log(gamesLost)
    	});
    });
    
    getMostGamesWon(function(res) {
    	var mostWins = $('#mostWins').html();
    	$('#mostWins').html(mostWins + " " + res.firstName + " " + res.lastName);
    	
    	gamesWon(res.id, function(wins) {
    		var Wins = $('#gamesWon').html();
    		$('#gamesWon').html(Wins + " " + wins);
    	});
    	
    	gamesPlayed(res.id, function(games) {
        	var gamesPlayed = $('#gamesPlayed').html();
        	$('#gamesPlayed').html(gamesPlayed + " " + games) ;
        });
    	
    	
    });
    
    getMostSpotlightTime(function(res) {
    	var mostTime = $('#mostTime').html();
    	$('#mostTime').html(mostTime + " " + res.firstName + " " + res.lastName);
    	
    	spotlightTime(res.id, function(time) {
    		var totalTime = $('#totalTime').html();
    		$('#totalTime').html(totalTime + " " + time);
    	})
    	
    	gamesPlayed(res.id, function(games) {
        	var gamesPlayed = $('#tGamesPlayed').html();
        	$('#tGamesPlayed').html(gamesPlayed + " " + games) ;
        });
    });
    
    getMostVotes(function(res) {
    	var mostVotes = $('#mostVotes').html();
    	$('#mostVotes').html(mostVotes + " " + res.firstName + " " + res.lastName);
    	
    	totalVotes(res.id, function(votes) {
    		var totalVotes = $('#totalVotes').html();
    		$('#totalVotes').html(totalVotes + " " + votes);
    	})
    	
    	gamesPlayed(res.id, function(games) {
        	var gamesPlayed = $('#vGamesPlayed').html();
        	$('#vGamesPlayed').html(gamesPlayed + " " + games) ;
        });
    	
    });
    
});
