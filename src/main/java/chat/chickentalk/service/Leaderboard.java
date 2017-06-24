package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.List;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;

public class Leaderboard {
	Dao dao = DaoImpl.getInstance();
	
	//returns all the votes from rounds a user has won
	public int getWinningVotes(User u) {
		int winningVotes = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(r.getWinnerId() == u.getId()) {
				winningVotes += r.getWinnerVotes();
			}
		}
		System.out.println(winningVotes);
		return winningVotes;
	}
	
	//returns all the votes from rounds a user has lost
	public int getLosingVotes(User u) {
		int losingVotes = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(r.getLoserId() == u.getId()) {
				losingVotes += r.getLoserVotes();
			}
		}
		System.out.println(losingVotes);
		return losingVotes;
	}
	
	//returns all votes a user has recieved
	public int totalVotes(User u) {
		int votesRecieved = getLosingVotes(u) + getWinningVotes(u);
		System.out.println(votesRecieved);
		return votesRecieved;
	}
	
	//returns games played by user
	public int gamesPlayed(User u) {
		int gamesPlayed = 0;
		List<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(u.getId() == r.getWinnerId() || u.getId() == r.getLoserId()) {
				gamesPlayed++;
			}
		}
		System.out.println(gamesPlayed);
		return gamesPlayed;
	}
	
	//returns the games won by a user
	public int gamesWon(User u) {
		int gamesWon = 0;
		List<Round> rounds = new ArrayList<Round>();

		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(u.getId() == r.getWinnerId()) {
				gamesWon++;
			}
		}
		System.out.println(gamesWon);
		return gamesWon++;
	}
	
	//returns time in spotlight !!!!LOOK INTO BETTER WAY!!!!
	public String spotlightTime(User u) {
		List<Round> rounds = new ArrayList<Round>();
		int totalSeconds = 0;
		int totalMinutes = 0;
		int totalHours = 0;
		String totalTime = "";
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(u.getId() == r.getWinnerId() || u.getId() == r.getLoserId()) {
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
}
