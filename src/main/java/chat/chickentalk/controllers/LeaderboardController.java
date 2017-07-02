package chat.chickentalk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chat.chickentalk.model.LeaderBoard;
import chat.chickentalk.model.User;
import chat.chickentalk.service.LeaderboardService;

@Controller
public class LeaderboardController {
	@Autowired
	private LeaderboardService svc;
	
	@RequestMapping(value = "leaderboard", method = RequestMethod.GET)
    public String getLeaderboard() {
        return "leaderboard";
    }
//
//	@ResponseBody @RequestMapping(value = "/leaderboard/winningVotes/{userId}", method = RequestMethod.GET)
//	public int getWinningVotes(@PathVariable int userId) {
//		return svc.getWinningVotes(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/losingVotes/{userId}", method = RequestMethod.GET)
//	public int getLosingVotes(@PathVariable int userId) {
//		return svc.getLosingVotes(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/totalVotes/{userId}", method = RequestMethod.GET)
//	public int getTotalVotes(@PathVariable int userId) {
//		return svc.totalVotes(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/gamesPlayed/{userId}", method = RequestMethod.GET)
//	public int getGamesPlayed(@PathVariable int userId) {
//		return svc.gamesPlayed(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/gamesWon/{userId}", method = RequestMethod.GET)
//	public int getGamesWon(@PathVariable int userId) {
//		return svc.gamesWon(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/gamesLost/{userId}", method = RequestMethod.GET)
//	public int getGamesLost(@PathVariable int userId) {
//		return svc.gamesLost(userId);
//	}
//	
//	@ResponseBody @RequestMapping(value = "/leaderboard/spotlightTime/{userId}", method = RequestMethod.GET)
//	public String getSpotlightTime(@PathVariable int userId) {
//		return svc.spotlightTime(userId);
//	}
//	
	@ResponseBody @RequestMapping(value = "/leaderboard/LeaderBoard/{num}", method = RequestMethod.GET)
    public LeaderBoard getLeaderBoard(@PathVariable int num) {
		return svc.getLeaderBoard(num);
    }

	
	
}
