package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;
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
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(r.getWinnerId() == id) {
				winningVotes += r.getWinnerVotes();
			}
		}
		//System.out.println(winningVotes);
		return winningVotes;
	}
	
	//returns all the votes from rounds a user has lost
	public int getLosingVotes(int id) {
		int losingVotes = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(r.getLoserId() == id) {
				losingVotes += r.getLoserVotes();
			}
		}
		//System.out.println(losingVotes);
		return losingVotes;
	}
	
	//returns all votes a user has recieved
	public int totalVotes(int id) {
		int votesRecieved = getLosingVotes(id) + getWinningVotes(id);
		//System.out.println(votesRecieved);
		return votesRecieved;
	}
	
	//returns games played by user
	public int gamesPlayed(int id) {
		int gamesPlayed = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
				gamesPlayed++;
			}
		}
		//System.out.println(gamesPlayed);
		return gamesPlayed;
	}
	
	//returns the games won by a user
	public int gamesWon(int id) {
		int gamesWon = 0;
		List<Round> rounds = new ArrayList<Round>();

		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId()) {
				gamesWon++;
			}
		}
		//System.out.println(gamesWon);
		return gamesWon;
	}
	
	//returns time in spotlight !!!!LOOK INTO BETTER WAY!!!!
	public String spotlightTime(int id) {
		List<Round> rounds = new ArrayList<Round>();
		int totalSeconds = 0;
		int totalMinutes = 0;
		int totalHours = 0;
		String totalTime = "";
		rounds = dao.getAllRounds();
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
		System.out.println(totalTime = ("Hours: " + totalHours + " Minutes: " + totalMinutes + " Seconds: " + totalSeconds)); 
		return totalTime;
	}
	
	//gets spotlight time in milliseconds, used to find who has the most time in spotlight
	public long slt(int id) {
		long milliseconds = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(id == r.getWinnerId() || id == r.getLoserId()) {
				milliseconds += (r.getEndDate().getTime()) - (r.getStartDate().getTime());
			}
		}
		System.out.println("time " + milliseconds);
		return milliseconds;
	}
	
	//GET USER ID WITH MOST WINS, GAMES PLAYED, MOST SPOTLIGHT TIME
	//returns id of user with most games played
	public int mostGames() {
		List<User> users = new ArrayList<User>();
		int mostGames = 0;
		int mostGamesUser = 0;
		users = dao.getAllUsers();
		for(User u : users) {
			if(gamesPlayed(u.getId()) > mostGames) {
				mostGames = gamesPlayed(u.getId());
				mostGamesUser = u.getId();
			}
		}
		//System.out.println("most games played id " + mostGamesUser);
		return mostGamesUser;
	}
	
	public int mostWins() {
		List<User> users = new ArrayList<User>();
		int mostWins = 0;
		int mostWinsUser = 0;
		users = dao.getAllUsers();
		for(User u : users) {
			if(gamesWon(u.getId()) > mostWins) {
				mostWins = gamesWon(u.getId());
				mostWinsUser = u.getId();
			}
		}
		//System.out.println("Most wins id " + mostWinsUser);
		return mostWinsUser;
	}
	
	public int mostSpotlightTime() {
		List<User> users = new ArrayList<User>();
		long mostTime = 0;
		int mostTimeUser = 0;
		users = dao.getAllUsers();
		for(User u : users) {
			if(slt(u.getId()) > mostTime) {
				mostTime = slt(u.getId());
				mostTimeUser = u.getId();
			}
		}
		System.out.println("Most time id " + mostTimeUser);
		return mostTimeUser;
	}
}
