<%@ include file="header.jspf" %>

<c:choose>
<c:when test="${session.getAttribute('user') != null}">

<div id="leaderboard" class="container-fluid" ng-controller="leaderBoardCtrl">
	<div id="mostGamesContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Games</em></h1>
		<table class="table table-hover table-striped text-center text-center">
			<thead>
				<tr>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Games Played </th>
					<th>Games Won </th>
					<th>Games Lost </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostGames">
				    <td width="5%">{{$index +1}}</td>
					<td style = "text-align: left; padding-left: 5em" width="20%">{{person.lastName}}, {{person.firstName}}</td>
				  
					<td>{{person.playedGames}}</td>
				  
					<td>{{person.wonGames}}</td>
					  
					<td>{{person.lostGames}}</td>
					
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="mostWinsContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Wins</em></h1>
		<table class="table table-hover table-striped text-center text-center">
			<thead>
				<tr>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Games Won </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostWins">
					<td width="5%">{{$index +1}}</td>
					<td style = "text-align: left; padding-left: 5em" width="20%">{{person.lastName}}, {{person.firstName}}</td>
					<td>{{person.wonGames}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="mostVotesContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Votes</em></h1>
		<table class="table table-hover table-striped text-center text-center">
			<thead>
				<tr>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Total Votes</th>
					<th>Games Played</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostVotes">
					<td width="5%">{{$index +1}}</td>
					<td style = "text-align: left; padding-left: 5em" width="20%">{{person.lastName}}, {{person.firstName}}</td>
					<td>{{person.voteTotal}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="mostTimeContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Time</em></h1>
		<table class="table table-hover table-striped text-center text-center">
			<thead>
				<tr>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Total Time </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostTime">
					<td width="5%">{{$index +1}}</td>
					<td style = "text-align: left; padding-left: 5em" width="20%">{{person.lastName}}, {{person.firstName}}</td>
					<td>{{person.spotlightTime}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>

    </c:when>
    <c:otherwise>
        <% response.sendRedirect("403"); %>
    </c:otherwise>
</c:choose>

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
