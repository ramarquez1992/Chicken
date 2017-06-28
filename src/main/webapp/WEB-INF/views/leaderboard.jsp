<%@ include file="header.jspf" %>
<!-- Leaderboard container-->
<!--  
<div id="leaderboard" ng-app="leaderboard">
	<div ng-controller="mostGamesCtrl" ng-init"firstName" ng-init"lastName ng-init"gamesPlayed ng-init"gamesWon" ng-init"gamesLost">
	<h1>Most Games</h1>
		<table>
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Games Played</th>
					<th>Games Won</th>
					<th>Games Lost</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostGames">
					<td>{{firstName}}</td>
					<td>{{LastName}}</td>
					<td>{{gamesPlayed}}</td>
					<td>{{gamesWon}}</td>
					<td>{{gamesLost}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
-->

<div>
<h1 id="mostGames">Most Games </h1>
<h3 id="mgGamesPlayed">Games Played </h3>
<h3 id="mgGamesWon">Games Won</h3>
<h3 id="mgGamesLost">Games Lost</h3>
</div>

<div>
<h1 id="mostWins">Most Wins </h1>
<h3 id="gamesWon">Games Won </h3>
<h3 id="gamesPlayed">Games Played</h3>
</div>

<div>
<h1 id ="mostTime">Most Time </h1>
<h3 id="totalTime">Total Time</h3>
<h3 id="tGamesPlayed">Games Played</h3>
</div>

<div>
<h1 id ="mostVotes">Most Votes</h1>
<h3 id="totalVotes">Total Votes</h3>
<h3 id="vGamesPlayed">Games Played</h3>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
<script src="static/js/leaderboard.js"></script>
<%@ include file="footer.jspf" %>
