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
		User u = dao.getUserById(2);
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
