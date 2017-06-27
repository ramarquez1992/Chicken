package chat.chickentalk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import chat.chickentalk.service.LeaderboardService;

@Controller
public class LeaderboardController {
	@Autowired
	private LeaderboardService svc;

	@ResponseBody @RequestMapping(value = "/leaderboard/winningVotes/{userId}", method = RequestMethod.GET)
	public int getWinningVotes(@PathVariable int userId) {
		return svc.getWinningVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/losingVotes/{userId}", method = RequestMethod.GET)
	public int getLosingVotes(@PathVariable int userId) {
		return svc.getLosingVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/totalVotes/{userId}", method = RequestMethod.GET)
	public int getTotalVotes(@PathVariable int userId) {
		return svc.totalVotes(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/gamesPlayed/{userId}", method = RequestMethod.GET)
	public int getGamesPlayed(@PathVariable int userId) {
		return svc.gamesPlayed(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/gamesWon/{userId}", method = RequestMethod.GET)
	public int getGamesWon(@PathVariable int userId) {
		return svc.gamesWon(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/spotlightTime/{userId}", method = RequestMethod.GET)
	public String getSpotlightTime(@PathVariable int userId) {
		return svc.spotlightTime(userId);
	}
	
	@ResponseBody @RequestMapping(value = "/leaderboard/mostGamesPlayed", method = RequestMethod.GET)
    public int getMostGamesPlayed() {
		return svc.mostGames();
    }
	
	@ResponseBody @RequestMapping(value = "/leaderboard/mostGamesWon", method = RequestMethod.GET)
    public int getMostGamesWon() {
		return svc.mostWins();
    }
	
	@ResponseBody @RequestMapping(value = "/spotlight/mostSpotlightTime", method = RequestMethod.GET)
    public int getMostSpotlightTime() {
		return svc.mostSpotlightTime();
    }
	
	
}
