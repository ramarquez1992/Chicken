package chat.chickentalk.service;

import java.util.*;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {
	@Autowired
    Dao dao;

	//returns all the votes from rounds a user has won
	public int getWinningVotes(int id) {
		int winningVotes = 0;
		List<Round> rounds = dao.getAllRounds();
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
		List<Round> rounds = dao.getAllRounds();
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
		List<Round> rounds = dao.getAllRounds();
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
		List<Round> rounds = dao.getAllRounds();
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
		List<Round>	rounds = dao.getAllRounds();
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
		totalTime = ("Hours: " + totalHours + " Minutes: " + totalMinutes + " Seconds: " + totalSeconds); 
		return totalTime;
	}
	
	//gets spotlight time in milliseconds, used to find who has the most time in spotlight
	public long slt(int id) {
		long milliseconds = 0;
		List<Round> rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
				milliseconds += (r.getEndDate().getTime()) - (r.getStartDate().getTime());
			}
		}
		return milliseconds;
	}
	
	//GET USER ID WITH MOST WINS, GAMES PLAYED, MOST SPOTLIGHT TIME
	//returns id of user with most games played
	public List<User> mostGames(int num) {
		List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		Collections.sort(users, (i1, i2) -> (gamesPlayed(i1.getId()) - (gamesPlayed(i2.getId()))));
		Collections.reverse(users);
		List<User> mostGamesUsers = new ArrayList<User>(users.subList(0, num));
		return mostGamesUsers;
	}
	
	public List<User> mostWins(int num) {
		List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		Collections.sort(users, (i1, i2) -> (gamesWon(i1.getId()) - (gamesWon(i2.getId()))));
		Collections.reverse(users);
		List<User> mostWinsUsers = new ArrayList<User>(users.subList(0, num));
		return mostWinsUsers;
	}
	
	public List<User> mostSpotlightTime(int num) {
		List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		Collections.sort(users, (i1, i2) -> Long.compare(slt(i1.getId()), slt(i2.getId())));
		Collections.reverse(users);
		List<User> mostTimeUsers = new ArrayList<User>(users.subList(0, num));
		return mostTimeUsers;
	}
	
	public List<User> mostVotes(int num) {
		List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		Collections.sort(users, (i1, i2) -> (totalVotes(i1.getId()) - (totalVotes(i2.getId()))));
		Collections.reverse(users);
		List<User> mostVotesUsers = new ArrayList<User>(users.subList(0, num));
		return mostVotesUsers;
	}
}
