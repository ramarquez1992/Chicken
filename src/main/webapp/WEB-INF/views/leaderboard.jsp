<%@ include file="header.jspf" %>

<div id="leaderboard" class="container-fluid" ng-controller="leaderBoardCtrl">
	<div id="mostGamesContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Games</em></h1>
		<table class="table table-hover table-striped">
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
	
	<div id="mostWinsContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Wins</em></h1>
		<table class="table table-hover table-striped">
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
	
	<div id="mostVotesContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Votes</em></h1>
		<table class="table table-hover table-striped">
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
	
	<div id="mostTimeContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Time</em></h1>
		<table class="table table-hover table-striped">
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
	getLeaderBoard(5, function(res){
		$scope.mostGames = res.mostGames;
		$scope.mostWins = res.mostWins;
		$scope.mostVotes = res.mostVotes;
		$scope.mostTime = res.mostSpotlightTime;
		$scope.$apply();
	});
});

</script>

<%@ include file="footer.jspf" %>
