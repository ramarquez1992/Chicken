package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	public int gamesLost(int id) {
		int gamesLost = (gamesPlayed(id) - gamesWon(id)); 
	//	System.out.println("games Lost " + gamesLost);
		return gamesLost;
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
	//	System.out.println(totalTime = ("Hours: " + totalHours + " Minutes: " + totalMinutes + " Seconds: " + totalSeconds)); 
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
	//	System.out.println("time " + milliseconds);
		return milliseconds;
	}
	
	//GET USER ID WITH MOST WINS, GAMES PLAYED, MOST SPOTLIGHT TIME
	//returns id of user with most games played
	public List<User> mostGames(int num) {
		List<User> users = new ArrayList<User>();
//		int mostGames = 0;
//		User mostGamesUser = null;
		users = dao.getAllUsers();
//		for(User u : users) {
//			if(gamesPlayed(u.getId()) > mostGames) {
//				mostGames = gamesPlayed(u.getId());
//				mostGamesUser = u;
//			}
//		}
//		//System.out.println("most games played id " + mostGamesUser);
//		return mostGamesUser;
		Collections.sort(users, (i1, i2) -> gamesPlayed(i1.getId() - gamesPlayed(i2.getId())));
		List<User> mostGamesUsers = new ArrayList<User>(users.subList(0, num));
		System.out.println("Finall list " + mostGamesUsers);
			for(User u : mostGamesUsers) {
				System.out.println("user id " + u.getId());
				System.out.println("games played " + gamesPlayed(u.getId()));
			}
		return mostGamesUsers;
	}
	
	public List<User> mostWins(int num) {
		List<User> users = new ArrayList<User>();
//		int mostWins = 0;
//		User mostWinsUser = null;
		users = dao.getAllUsers();
//		for(User u : users) {
//			if(gamesWon(u.getId()) > mostWins) {
//				mostWins = gamesWon(u.getId());
//				mostWinsUser = u;
//			}
//		}
		//System.out.println("Most wins id " + mostWinsUser);
//		return mostWinsUser;
		Collections.sort(users, (i1, i2) -> gamesWon(i1.getId() - gamesWon(i2.getId())));
		List<User> mostWinsUsers = new ArrayList<User>(users.subList(0, num));
		System.out.println("Final list " + mostWinsUsers);
			for(User u : mostWinsUsers) {
				System.out.println("user id " + u.getId());
				System.out.println("games won " + gamesWon(u.getId()));
			}
		return mostWinsUsers;
	}
	
	public List<User> mostSpotlightTime(int num) {
		List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		
		//TODO: figure out wtf is wrong here!!!!!!!
		Collections.sort(users, (i1, i2) -> slt(i1.getId() - slt(i2.getId())));
		List<User> mostTimeUsers = new ArrayList<User>(users.subList(0, num));
		System.out.println("Final list " + mostTimeUsers);
		for(User u : mostTimeUsers) {
			System.out.println("user id " + u.getId());
			System.out.println("spotlight time " + spotlightTime(u.getId()));
		}
		return mostTimeUsers;
	}
	
	public List<User> mostVotes(int num) {
		List<User> users = new ArrayList<User>();
//		long mostVotes = 0;
//		User mostVotesUser = null;
		users = dao.getAllUsers();
//		for(User u : users) {
//			if(totalVotes(u.getId()) > mostVotes) {
//				mostVotes = totalVotes(u.getId());
//				mostVotesUser = u;
//			}
//		}
	//	System.out.println("most votes user id " + mostVotesUser);
		Collections.sort(users, (i1, i2) -> totalVotes(i1.getId() - totalVotes(i2.getId())));
		List<User> mostVotesUsers = new ArrayList<User>(users.subList(0, num));
		System.out.println("Final list " + mostVotesUsers);
			for(User u : mostVotesUsers) {
				System.out.println("user id " + u.getId());
				System.out.println("games won " + gamesWon(u.getId()));
			}
		return mostVotesUsers;
	}
}
