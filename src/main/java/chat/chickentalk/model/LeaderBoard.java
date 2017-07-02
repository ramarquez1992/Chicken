package chat.chickentalk.model;

import java.util.List;

public class LeaderBoard {
	List<User> mostGames;
	List<User> mostWins;
	List<User> mostSpotlightTime;
	List<User> mostVotes;
	
	public LeaderBoard(){}

	@Override
	public String toString() {
		return "LeaderBoard [mostGames=" + mostGames + ", mostWins=" + mostWins + ", mostSpotlightTime="
				+ mostSpotlightTime + ", mostVotes=" + mostVotes + "]";
	}

	public LeaderBoard(List<User> mostGames, List<User> mostWins, List<User> mostSpotlightTime, List<User> mostVotes) {
		super();
		this.mostGames = mostGames;
		this.mostWins = mostWins;
		this.mostSpotlightTime = mostSpotlightTime;
		this.mostVotes = mostVotes;
	}

	public List<User> getMostGames() {
		return mostGames;
	}

	public void setMostGames(List<User> mostGames) {
		this.mostGames = mostGames;
	}

	public List<User> getMostWins() {
		return mostWins;
	}

	public void setMostWins(List<User> mostWins) {
		this.mostWins = mostWins;
	}

	public List<User> getMostSpotlightTime() {
		return mostSpotlightTime;
	}

	public void setMostSpotlightTime(List<User> mostSpotlightTime) {
		this.mostSpotlightTime = mostSpotlightTime;
	}

	public List<User> getMostVotes() {
		return mostVotes;
	}

	public void setMostVotes(List<User> mostVotes) {
		this.mostVotes = mostVotes;
	}
}