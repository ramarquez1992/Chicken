<%@ include file="header.jspf" %>

<div id="leaderboard" class="container-fluid" ng-controller="leaderBoardCtrl">
	<div id="mostGamesContainer" class="leaderboardContainer white-container">
		<h1>Most <em>Games</em></h1>
		<table class="table table-hover table-striped text-center text-center">
			<thead>
				<tr>
					<th style = "display: none;">User Id</th>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Games Played </th>
					<th>Games Won </th>
					<th>Games Lost </th>
					
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostGames" ng-click="showProfile(person)">
					<td style = "display: none;" class="selectid">{{person.id}}</td>
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
					<th style = "display: none;">User Id</th>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Games Won </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostWins" ng-click="showProfile(person)">
					<td style = "display: none;" class="selectid">{{person.id}}</td>
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
					<th style = "display: none;">User Id</th>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Total Votes</th>
					<th>Games Played</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostVotes" ng-click="showProfile(person)">
					<td style = "display: none;" class="selectid">{{person.id}}</td>
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
					<th style = "display: none;">User Id</th>
					<th width="5%">Rank</th>
					<th style = "text-align: left; padding-left: 5em" width="20%">Name </th>
					<th>Total Time </th>
					<th>Games Played </th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat = "person in mostTime" ng-click="showProfile(person)">
					<td style = "display: none;" class="selectid">{{person.id}}</td>
					<td width="5%">{{$index +1}}</td>
					<td style = "text-align: left; padding-left: 5em" width="20%">{{person.lastName}}, {{person.firstName}}</td>
					<td>{{person.spotlightTime}}</td>
					<td>{{person.playedGames}}</td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>

<div class="modal fade" id="leaderboardProfile">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">User Profile</h4>
            </div>
            <div class="modal-body">
                <img id="avatar" src=""/>
                <p id="fullName"></p>
                <p id="games"></p>
                <p id="wins"></p>
                <p id="spotlight"></p>
                <p id="votes"></p>
                <p id="votesCast"></p>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
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
	
	$scope.showProfile = function(person) {
		$('#leaderboardProfile').modal({});
		var selectId = person.id;
		
		getUser(selectId, function(res) {
            $("#fullName").text("Name: " + res.firstName + " " + res.lastName);

            $("#votesCast").text("Total Votes Cast: " + res.votesCast);
           

            $("#avatar").attr("src", res.avatar);

            gamesPlayed(res.id, function(res) {
                $("#games").text("Total Games Played: " + res);
            });

            gamesWon(res.id, function(res) {
                $("#wins").text("Total Wins: " + res);
            });

            spotlightTime(res.id, function(res) {
                $("#spotlight").text("Time in the Spotlight: " + res);
            });

            totalVotes(res.id, function(res) {
                $("#votes").text("Total Votes Recieved: " + res);
            });
        });
	};
});





</script>

<%@ include file="footer.jspf" %>
