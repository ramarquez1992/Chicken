package chat.chickentalk.test;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.pojos.User;
import chat.chickentalk.pojos.UserStatus;

public class test {

	public static void main(String[] args) {
		DaoImpl dao = DaoImpl.getInstance();
		
		User u = new User();
		
		
		UserStatus us = new UserStatus("Shadow Ban");
		
		//dao.createUser(u);
		dao.getUserById(451);
		
		System.out.println(u.toString());
		
		
		
		
		dao.createUserStatus(us);
		
	}

}
