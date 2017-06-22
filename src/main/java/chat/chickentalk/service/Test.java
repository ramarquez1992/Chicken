package chat.chickentalk.service;

import java.util.ArrayList;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;

public class Test {
	public static void main(String[] args) {
		DaoImpl dao = DaoImpl.getInstance();
	//	User u = dao.getUserById(452);
//		System.out.println(u.toString());
//		Round r = dao.getRoundById(1);
//		System.out.println(r.toString());
//		
//		Leaderboard l = new Leaderboard();
//		l.userWins(u);
		ArrayList<Round> rounds = new ArrayList<Round>();
		rounds = dao.getAllRounds();
		System.out.println(rounds);
		
		
		
	}
}
