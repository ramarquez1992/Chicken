package chat.chickentalk.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import chat.chickentalk.service.LeaderboardService;
import chat.chickentalk.service.SpotlightService;

@Controller
public class LeaderboardController {

	@ResponseBody @RequestMapping(value = "/leaderboard/winningVotes/{userId}", method = RequestMethod.GET)
	public int getWinningVotes(@PathVariable int userId) {
		return LeaderboardService.getInstance().getWinningVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/losingVotes/{userId}", method = RequestMethod.GET)
	public int getLosingVotes(@PathVariable int userId) {
		return LeaderboardService.getInstance().getLosingVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/totalVotes/{userId}", method = RequestMethod.GET)
	public int getTotalVotes(@PathVariable int userId) {
		return LeaderboardService.getInstance().totalVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/gamesPlayed/{userId}", method = RequestMethod.GET)
	public int getGamesPlayed(@PathVariable int userId) {
		return LeaderboardService.getInstance().gamesPlayed(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/gamesWon/{userId}", method = RequestMethod.GET)
	public int getGamesWon(@PathVariable int userId) {
		return LeaderboardService.getInstance().gamesWon(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/spotlightTime/{userId}", method = RequestMethod.GET)
	public String getSpotlightTime(@PathVariable int userId) {
		return LeaderboardService.getInstance().spotlightTime(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/mostGamesPlayed", method = RequestMethod.GET)
    public int getMostGamesPlayed() {
		return LeaderboardService.getInstance().mostGames();
    }
	
	@ResponseBody @RequestMapping(value = "/leaderboard/mostGamesWon", method = RequestMethod.GET)
    public int getMostGamesWon() {
		return LeaderboardService.getInstance().mostWins();
    }
	
	@ResponseBody @RequestMapping(value = "/spotlight/mostSpotlightTime", method = RequestMethod.GET)
    public int getMostSpotlightTime() {
		return LeaderboardService.getInstance().mostSpotlightTime();
    }
	
	
}
