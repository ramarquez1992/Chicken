package chat.chickentalk.service;

import java.util.ArrayList;
import java.util.List;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;

public class Test {
	public static void main(String[] args) {
		Dao dao = DaoImpl.getInstance();
		LeaderboardService lbs = LeaderboardService.getInstance();

		System.out.println("before");
		System.out.println("id 1  "  + lbs.slt(1));
		System.out.println("id one " + lbs.spotlightTime(1));
		System.out.println("id 2  "  + lbs.slt(2));
		System.out.println("id two " + lbs.spotlightTime(2));
////		System.out.println("id 3  "  + lbs.slt(3));
		System.out.println("answer " +  lbs.mostSpotlightTime());
		System.out.println("after");
	//	lbs.mostSpotlightTime();
		
	
		
//		System.out.println(u.toString());
//		Round r = dao.getRoundById(1);
//		System.out.println(r.toString());
//		
//		LeaderboardService l = new LeaderboardService();
//		System.out.println("before");
//		l.spotlightTime(u);
//		
//		System.out.println("after");
//		
//		
//		
	}
}
