package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.LeaderBoard;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;

@Service
public class LeaderboardService {
	@Autowired
    Dao dao;
	
	public List<User> users;
	public List<Round> rounds;
	LeaderBoard leaderBoard = new LeaderBoard();
	
	public List<User> getUsers() {
		if(users == null) {
		users =	Collections.synchronizedList(dao.getAllUsers());
			System.out.println(users);
			return users;
		}
		return users;
	}

	public List<Round> getRounds() {
		if(rounds == null) {
			rounds = Collections.synchronizedList(dao.getAllRounds());
			System.out.println(rounds);
			return rounds;
		}
		return rounds;
	}

	LeaderBoard leaderboard = new LeaderBoard();
	
	//returns all the votes from rounds a user has won
	public int getWinningVotes(int id) {
		int winningVotes = 0;
		getRounds();
		for(Round r : rounds) {
			if(r.getWinnerId() == id) {
				winningVotes += r.getWinnerVotes();
			}
		}
		return winningVotes;
	}
	
	//returns all the votes from rounds a user has lost
	public int getLosingVotes(int id) {
		int losingVotes = 0;
		getRounds();
		for(Round r : rounds) {
			if(r.getLoserId() == id) {
				losingVotes += r.getLoserVotes();
			}
		}
		return losingVotes;
	}
	
	//returns all votes a user has recieved
	public int totalVotes(int id) {
		int votesRecieved = getLosingVotes(id) + getWinningVotes(id);
		return votesRecieved;
	}
	
	//returns games played by user
	public int gamesPlayed(int id) {
		int gamesPlayed = 0;
		getRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
				gamesPlayed++;
			}
		}
		return gamesPlayed;
	}
	
	//returns the games won by a user
	public int gamesWon(int id) {
		int gamesWon = 0;
		getRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId()) {
				gamesWon++;
			}
		}
		return gamesWon;
	}
	
	public int gamesLost(int id) {
		int gamesLost = (gamesPlayed(id) - gamesWon(id)); 
		return gamesLost;
	}
	
	//returns time in spotlight !!!!LOOK INTO BETTER WAY!!!!
	public String spotlightTime(int id) {
		getRounds();
		int totalSeconds = 0;
		int totalMinutes = 0;
		int totalHours = 0;
		String totalTime = "";

		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
			long milliseconds = (r.getEndDate().getTime()) - (r.getStartDate().getTime());
			int seconds = (int) milliseconds / 1000;
			int hours = seconds / 3600;
			int minutes = (seconds % 3600) / 60;
			seconds = (seconds % 3600) % 60;
			
			totalSeconds += seconds;
			totalMinutes += minutes;
			totalHours += hours;
			}
		}
		totalTime = (totalHours + " hrs " + totalMinutes + " mins " + totalSeconds + " secs"); 
		return totalTime;
	}
	
	//gets spotlight time in milliseconds, used to find who has the most time in spotlight
	public long slt(int id) {
		long milliseconds = 0;
		getRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
				milliseconds += (r.getEndDate().getTime()) - (r.getStartDate().getTime());
			}
		}
		return milliseconds;
	}
	
	
	
	public LeaderBoard getLeaderBoard(int num){
		users = dao.getAllUsers();
		rounds = dao.getAllRounds();
		mostGames(num);
		mostWins(num);
		mostVotes(num);
		mostSpotlightTime(num);
		
		System.out.println(leaderBoard.toString());
		
		return leaderBoard;
	}
	
	//GET USER ID WITH MOST WINS, GAMES PLAYED, MOST SPOTLIGHT TIME
	//returns id of user with most games played
	public synchronized void mostGames(int num) {
		getUsers();
		Collections.sort(users, (i1, i2) -> (gamesPlayed(i1.getId()) - (gamesPlayed(i2.getId()))));
		Collections.reverse(users);
		
		List<User> temp = Collections.synchronizedList(new ArrayList<User>(users.subList(0, num)));
		
		for(User u : temp){
			u.playedGames = gamesPlayed(u.getId());
			u.lostGames = gamesLost(u.getId());
			u.wonGames = gamesWon(u.getId());
		}
		leaderBoard.setMostGames(temp);
	}
	
	public synchronized void mostWins(int num) {
		getUsers();
		Collections.sort(users, (i1, i2) -> (gamesWon(i1.getId()) - (gamesWon(i2.getId()))));
		Collections.reverse(users);
		
		List<User> temp = Collections.synchronizedList(new ArrayList<User>(users.subList(0, num)));

		for(User u : temp){
			u.playedGames = gamesPlayed(u.getId());
			u.wonGames = gamesWon(u.getId());
		}
		leaderBoard.setMostWins(temp);
	}
	
	public synchronized void mostVotes(int num) {
		getUsers();
		Collections.sort(users, (i1, i2) -> (totalVotes(i1.getId()) - (totalVotes(i2.getId()))));
		Collections.reverse(users);
		
		List<User> temp = Collections.synchronizedList(new ArrayList<User>(users.subList(0, num)));

		for(User u : temp){
			u.playedGames = gamesPlayed(u.getId());
			u.voteTotal = totalVotes(u.getId());
		}
		leaderBoard.setMostVotes(temp);
	}
	
	public synchronized void mostSpotlightTime(int num) {
		getUsers();
		Collections.sort(users, (i1, i2) -> Long.compare(slt(i1.getId()), slt(i2.getId())));
		Collections.reverse(users);
		
		List<User> temp = Collections.synchronizedList(new ArrayList<User>(users.subList(0, num)));

		for(User u : temp){
			u.spotlightTime = spotlightTime(u.getId());
			u.playedGames = gamesPlayed(u.getId());
		}
		leaderBoard.setMostSpotlightTime(temp);
	}
}
