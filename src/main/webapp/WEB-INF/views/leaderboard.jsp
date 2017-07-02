<%@ include file="header.jspf" %>
<!-- Leaderboard container-->
 
<div id="leaderboard" ng-controller="leaderBoardCtrl">
	<div>
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
				  
					<td>{{person.playedGames}}</td>
				  
					<td>{{person.wonGames}}</td>
					  
					<td>{{person.lostGames}}</td>
					
				</tr>
			</tbody>
		</table>
	</div>
	
	<div>
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
					<td>{{person.wonGames}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div>
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
					<td>{{person.voteTotal}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div>
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
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>


<script>

app.controller("leaderBoardCtrl", function($scope){
	getLeaderBoard(2, function(res){
		$scope.mostGames = res.mostGames;
		$scope.mostWins = res.mostWins;
		$scope.mostVotes = res.mostVotes;
		$scope.mostTime = res.mostSpotlightTime;
		$scope.$apply();
	});
});

// app.controller("mostGamesCtrl", function($scope) {
// 	$scope.mostGames = leaderBoard.mostGames;
// });

// //handles most wins table information
// app.controller("mostWinsCtrl", function($scope) {
// 	$scope.mostWins = leaderBoard.mostWins;
// });

// //handels most votes
// app.controller("mostVotesCtrl", function($scope) {
// 	$scope.mostVotes = leaderBoard.mostVotes;
// });

// //handles most time 
// app.controller("mostTimeCtrl", function ($scope) {
// 	$scope.mostTime = leaderBoard.mostSpotlightTime;
// });
</script>

<%@ include file="footer.jspf" %>
