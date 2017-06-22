package chat.chickentalk.service;

import java.util.ArrayList;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;

public class Leaderboard {
	DaoImpl dao = DaoImpl.getInstance();
	
	//get user winning votes
	public int getWinningVotes(User u) {
		int winningVotes = 0;
		ArrayList<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(r.getWinnerId() == u.getId()) {
				winningVotes += r.getWinnerVotes();
			}
		}
		return winningVotes;
	}
	
	//returns games played by user
	public int gamesPlayed(User u) {
		int gamesPlayed = 0;
		ArrayList<Round> rounds = new ArrayList<Round>();

		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			if(u.getId() == r.getWinnerId() || u.getId() == r.getLoserId()) {
				gamesPlayed++;
				System.out.println(gamesPlayed);
			}
		}
		return gamesPlayed;
	}
	
	public void userWins(User u) {
		int gamesWon = 0;
		ArrayList<Round> rounds = new ArrayList<Round>();

		rounds = dao.getAllRounds();
		for(Round r : rounds) {
			System.out.println(rounds.toString());
			if(u.getId() == r.getWinnerId()) {
				gamesWon++;
				System.out.println(gamesWon);
			}
		}
	}
}
