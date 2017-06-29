package chat.chickentalk.test;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.model.UserStatus;

public class TestingGrounds {
	AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
	Dao dao = appContext.getBean("dao", Dao.class);
	
	public static void main(String[] args) {
		TestingGrounds tg = new TestingGrounds();
		//tg.createDatabase();
		tg.createDB();
	}
  
	public void createDB(){
		UserStatus us = new UserStatus(0,"normal");
        UserStatus us2 = new UserStatus(1,"shadow ban");
        UserStatus us3 = new UserStatus(2,"permanent ban");
        UserStatus us4 = new UserStatus(3,"admin");
        UserStatus us5 = new UserStatus(4,"Chicken");
        
        /*
         * Dummy user for the database. Required! This user is given a loss when ties occur for example.
         * 
         * DM 6-27-17
         */
        User dummy = new User();
        dummy.setEmail("dummy@dummy.com");
        dummy.setPassword("9z_UZtX3AQqV'}h*xDeZR-#h)~/.PQ");
        dummy.setFirstName("Dummy");
        dummy.setLastName("Dummy");
        dummy.setBaby(true);
        dummy.setStatus(us);
        
        User u = new User();
        u.setEmail("zoro@rn.com");
        u.setPassword("password");
        u.setFirstName("Bob");
        u.setLastName("Hammer");
        u.setBaby(true);
        u.setStatus(us);

        User u2 = new User();
        u2.setEmail("CHICKED@Chickentalk.chat");
        u2.setPassword("password");
        u2.setFirstName("Richie");
        u2.setLastName("Chicken");
        u2.setBaby(false);
        u2.setStatus(us5);
        
        User u3 = new User();
        u3.setEmail("gladiator@glad.arena");
        u3.setPassword("password");
        u3.setFirstName("Maximus");
        u3.setLastName("Meridius");

        Round r = new Round();
        r.setLoserId(3);
        r.setWinnerId(2);
        r.setWinnerVotes(21);
        r.setLoserVotes(5);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        r.setEndDate(time);							//  MINS  SECS  MILLISECONDS
        time = new Timestamp(System.currentTimeMillis() - (3 * 60 * 1000));
        r.setStartDate(time);

        Round r2 = new Round();
        r2.setLoserId(2);
        r2.setWinnerId(3);
        r2.setWinnerVotes(11);
        r2.setLoserVotes(7);
        time = new Timestamp(System.currentTimeMillis());
        r2.setEndDate(time);						//  MINS  SECS  MILLISECONDS
        time = new Timestamp(System.currentTimeMillis() - (2 * 60 * 1000));
        r2.setStartDate(time);

        Round r3 = new Round();
        r3.setLoserId(2);
        r3.setWinnerId(3);
        r3.setWinnerVotes(11);
        r3.setLoserVotes(5);
        time = new Timestamp(System.currentTimeMillis());
        r3.setEndDate(time);						// 2.5 minutes difference
        time = new Timestamp(System.currentTimeMillis() - 150000);
        r3.setStartDate(time);
        
        Round r4 = new Round();
        r4.setLoserId(4);
        r4.setWinnerId(3);
        r4.setWinnerVotes(19);
        r4.setLoserVotes(9);
        time = new Timestamp(System.currentTimeMillis());
        r4.setEndDate(time);						// 1.3 min difference
        time = new Timestamp(System.currentTimeMillis() - 78000);
        r4.setStartDate(time);

        dao.createUserStatus(us);
        dao.createUserStatus(us2);
        dao.createUserStatus(us3);
        dao.createUserStatus(us4);
        dao.createUserStatus(us5);
        dao.createUser(dummy);
        dao.createUser(u);
        dao.createUser(u2);
        dao.createUser(u3);
        dao.createRound(r);
        dao.createRound(r2);
        dao.createRound(r3);
        dao.createRound(r4);

        // Printing out all of the test objects to the console.
        System.out.println("\n\n\nTEST DATA: \n\n" + us.toString());
        System.out.println(us2.toString());
        System.out.println(us3.toString());
        System.out.println(us4.toString());
        System.out.println(us5.toString());
        System.out.println("\nDO NOT USE: " + dummy.toString() + "\n");
        System.out.println(u.toString());
        System.out.println(u2.toString());
        System.out.println(u3.toString());
        System.out.println("\n" + r.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());
        System.out.println(r4.toString());
        
        
        User temp = dao.getUserByEmail("zoro@rn.com");
        System.out.println(temp.toString());

	}
}
