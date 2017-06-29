<%@ include file="header.jspf" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<!-- Leaderboard container-->
 
<div id="leaderboard" ng-app="leaderboard">
	<div ng-controller="mostGamesCtrl">
	<h1>Most Games</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First Name </th>
					<th>Last Name </th>
					<th>Games Played </th>
					<th>Games Won </th>
					<th>Games Lost </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostGames">
				   
					<td>{{person.firstName}}</td>
					<td>{{person.lastName}}</td>
				  
					<td>{{person.gamesPlayed}}</td>
				  
					<td>{{person.gamesWon}}</td>
					  
					<td>{{person.gamesLost}}</td>
					
				</tr>
			</tbody>
		</table>
	</div>
	
	<div ng-controller="mostWinsCtrl">
	<h1>Most Wins</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First Name </th>
					<th>Last Name </th>
					<th>Games Won </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostWins">
					<td>{{person.firstName}}</td>
					<td>{{person.lastName}}</td>
					<td>{{person.gamesWon}}</td>
					<td>{{person.gamesPlayed}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div ng-controller="mostVotesCtrl">
	<h1>Most Votes</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First Name </th>
					<th>Last Name </th>
					<th>Total Votes </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostVotes">
					<td>{{person.firstName}}</td>
					<td>{{person.lastName}}</td>
					<td>{{person.totalVotes}}</td>
					<td>{{person.gamesPlayed}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div ng-controller="mostTimeCtrl">
	<h1>Most Time</h1>
		<table class="table">
			<thead>
				<tr>
					<th>First Name </th>
					<th>Last Name </th>
					<th>Total Time </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostTime">
					<td>{{person.firstName}}</td>
					<td>{{person.lastName}}</td>
					<td>{{person.spotlightTime}}</td>
					<td>{{person.gamesPlayed}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>


<script>
	var Leaderboard = angular.module("leaderboard", []);
	//handles most games table information
	Leaderboard.controller("mostGamesCtrl", function($scope) {
		
		getMostGames(2, function(res) {
			$scope.mostGames = res;
			$scope.$apply();
			
			angular.forEach($scope.mostGames, function(item) {
				gamesPlayed(item.id, function(gamesPlayed) {
						item.gamesPlayed = gamesPlayed;
						$scope.$apply();
				});
			});	
			
			angular.forEach($scope.mostGames, function(item) {
				gamesWon(item.id, function(gamesWon) {
						item.gamesWon = gamesWon;
						$scope.$apply();
				});
			});	
			
			
			angular.forEach($scope.mostGames, function(item) {
				gamesLost(item.id, function(gamesLost) {
						item.gamesLost = gamesLost;
						$scope.$apply();
				});
			});		
			
		});	
	});
	
	//handles most wins table information
	Leaderboard.controller("mostWinsCtrl", function($scope) {
		getMostGamesWon(2, function(res) {
			$scope.mostWins = res;
			$scope.$apply();
			
			angular.forEach($scope.mostWins, function(item) {
				gamesWon(item.id, function(gamesWon) {
						item.gamesWon = gamesWon;
						$scope.$apply();
				});
			});	
			
			angular.forEach($scope.mostWins, function(item) {
				gamesPlayed(item.id, function(gamesPlayed) {
						item.gamesPlayed = gamesPlayed;
						$scope.$apply();
				});
			});	
			
		});
	});
	
	//handels most votes
	Leaderboard.controller("mostVotesCtrl", function($scope) {
		getMostVotes(2, function(res) {
			$scope.mostVotes = res;
			$scope.$apply();
			
			angular.forEach($scope.mostVotes, function(item) {
				totalVotes(item.id, function(totalVotes) {
						item.totalVotes = totalVotes;
						$scope.$apply();
				});
			});	
			
			angular.forEach($scope.mostVotes, function(item) {
				gamesPlayed(item.id, function(gamesPlayed) {
						item.gamesPlayed = gamesPlayed;
						$scope.$apply();
				});
			});	
			
		});
	});
	
	//handles most time 
	Leaderboard.controller("mostTimeCtrl", function ($scope) {
		getMostSpotlightTime(2, function(res) {
			$scope.mostTime = res;
			$scope.$apply();
	
			angular.forEach($scope.mostTime, function(item) {
				spotlightTime(item.id, function(spotlightTime) {
						item.spotlightTime = spotlightTime;
						$scope.$apply();
				});
			});	
			
			angular.forEach($scope.mostTime, function(item) {
				gamesPlayed(item.id, function(gamesPlayed) {
						item.gamesPlayed = gamesPlayed;
						$scope.$apply();
				});
			});	
			
		});
	});
</script>

<script src="static/js/leaderboard.js"></script>
<%@ include file="footer.jspf" %>
