package chat.chickentalk.controllers;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.model.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class LandingController {
    @Autowired
    Dao dao;

    @RequestMapping(value = { "", "/", "landing" }, method = RequestMethod.GET)
    public String getLanding() {
        return "landing";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }




    @RequestMapping(value = "createDatabase", method = RequestMethod.GET)
    public String createDatabase() {
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
        dummy.setPassword("9z_UZtX3AQqV'}h*xDeZR-#h)55e55ye5yy?P{?+_~/.PQ");
        dummy.setFirstName("Dummy");
        dummy.setLastName("Dummy");
        dummy.setBaby(true);
        dummy.setStatus(us);
        dummy.setVotesCast(0);

        User u = new User();
        u.setEmail("bhammer@gmail.com");
        u.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u.setFirstName("Bob");
        u.setLastName("Hammer");
        u.setBaby(true);
        u.setStatus(us4);
        u.setVotesCast(17);

        User u2 = new User();
        u2.setEmail("jchickson@gmail.com");
        u2.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u2.setFirstName("Jack");
        u2.setLastName("Chickson");
        u2.setBaby(false);
        u2.setStatus(us4);
        u2.setVotesCast(31);
        
        User u3 = new User();
        u3.setEmail("ddelite@gmail.com");
        u3.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u3.setFirstName("Dan");
        u3.setLastName("Delite");
        u3.setBaby(true);
        u3.setStatus(us);
        u3.setVotesCast(19);
        
        User u4 = new User();
        u4.setEmail("mshu@gmail.com");
        u4.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u4.setFirstName("Michelle");
        u4.setLastName("Shu");
        u4.setBaby(true);
        u4.setStatus(us);
        u4.setVotesCast(2);
        
        User u5 = new User();
        u5.setEmail("sholler@gmail.com");
        u5.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u5.setFirstName("Stacy");
        u5.setLastName("Holler");
        u5.setBaby(true);
        u5.setStatus(us);
        u5.setVotesCast(15);
        
        User u6 = new User();
        u6.setEmail("mholler@gmail.com");
        u6.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u6.setFirstName("Michael");
        u6.setLastName("Holler");
        u6.setBaby(true);
        u6.setStatus(us);
        u6.setVotesCast(11);

        User u7 = new User();
        u7.setEmail("rsheen@gmail.com");
        u7.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u7.setFirstName("Robert");
        u7.setLastName("Sheen");
        u7.setBaby(true);
        u7.setStatus(us);
        u7.setVotesCast(5);
        
        User u8 = new User();
        u8.setEmail("mstu@gmail.com");
        u8.setPassword("sha1:64000:18:nUm18HUiSOhB9WEaBa2132S3i8xPM5xs:l43vm04a7nh0k+GDK1oHabQ2");
        u8.setFirstName("Mary");
        u8.setLastName("Stu");
        u8.setBaby(true);
        u8.setStatus(us);
        u8.setVotesCast(8);
        
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis() - 20000);
        
        Round r = new Round();
        r.setLoserId(2);
        r.setWinnerId(3);
        r.setWinnerVotes(13);
        r.setLoserVotes(5);
        r.setEndDate(time);
        r.setStartDate(time2);

        Round r2 = new Round();
        r2.setLoserId(3);
        r2.setWinnerId(2);
        r2.setWinnerVotes(11);
        r2.setLoserVotes(7);
        r2.setEndDate(time);
        r2.setStartDate(time2);

        Round r3 = new Round();
        r3.setLoserId(4);
        r3.setWinnerId(3);
        r3.setWinnerVotes(9);
        r3.setLoserVotes(4);
        r3.setEndDate(time);
        r3.setStartDate(time2);

        Round r4 = new Round();
        r4.setLoserId(5);
        r4.setWinnerId(3);
        r4.setWinnerVotes(11);
        r4.setLoserVotes(7);
        r4.setEndDate(time);
        r4.setStartDate(time2);
        
        Round r5 = new Round();
        r5.setLoserId(5);
        r5.setWinnerId(3);
        r5.setWinnerVotes(18);
        r5.setLoserVotes(11);
        r5.setEndDate(time);
        r5.setStartDate(time2);
        
        Round r6 = new Round();
        r6.setLoserId(6);
        r6.setWinnerId(3);
        r6.setWinnerVotes(5);
        r6.setLoserVotes(2);
        r6.setEndDate(time);
        r6.setStartDate(time2);
        
        Round r7 = new Round();
        r7.setLoserId(7);
        r7.setWinnerId(3);
        r7.setWinnerVotes(3);
        r7.setLoserVotes(1);
        r7.setEndDate(time);
        r7.setStartDate(time2);
        
        Round r8 = new Round();
        r8.setLoserId(8);
        r8.setWinnerId(3);
        r8.setWinnerVotes(9);
        r8.setLoserVotes(7);
        r8.setEndDate(time);
        r8.setStartDate(time2);
        
        Round r9 = new Round();
        r9.setLoserId(9);
        r9.setWinnerId(5);
        r9.setWinnerVotes(12);
        r9.setLoserVotes(6);
        r9.setEndDate(time);
        r9.setStartDate(time2);
        
        Round r10 = new Round();
        r10.setLoserId(5);
        r10.setWinnerId(7);
        r10.setWinnerVotes(6);
        r10.setLoserVotes(4);
        r10.setEndDate(time);
        r10.setStartDate(time2);
        
        Round r11 = new Round();
        r11.setLoserId(7);
        r11.setWinnerId(8);
        r11.setWinnerVotes(10);
        r11.setLoserVotes(8);
        r11.setEndDate(time);
        r11.setStartDate(time2);
        
        Round r12 = new Round();
        r12.setLoserId(8);
        r12.setWinnerId(9);
        r12.setWinnerVotes(15);
        r12.setLoserVotes(6);
        r12.setEndDate(time);
        r12.setStartDate(time2);
        
        Round r13 = new Round();
        r13.setLoserId(9);
        r13.setWinnerId(5);
        r13.setWinnerVotes(7);
        r13.setLoserVotes(3);
        r13.setEndDate(time);
        r13.setStartDate(time2);
        
        Round r14 = new Round();
        r14.setLoserId(6);
        r14.setWinnerId(4);
        r14.setWinnerVotes(7);
        r14.setLoserVotes(3);
        r14.setEndDate(time);
        r14.setStartDate(time2);
        
        Round r15 = new Round();
        r15.setLoserId(6);
        r15.setWinnerId(2);
        r15.setWinnerVotes(11);
        r15.setLoserVotes(7);
        r15.setEndDate(time);
        r15.setStartDate(time2);
        
        Round r16 = new Round();
        r16.setLoserId(6);
        r16.setWinnerId(4);
        r16.setWinnerVotes(11);
        r16.setLoserVotes(9);
        r16.setEndDate(time);
        r16.setStartDate(time2);
        
        Round r17 = new Round();
        r17.setLoserId(4);
        r17.setWinnerId(3);
        r17.setWinnerVotes(6);
        r17.setLoserVotes(4);
        r17.setEndDate(time);
        r17.setStartDate(time2);
        
        Round r18 = new Round();
        r18.setLoserId(5);
        r18.setWinnerId(4);
        r18.setWinnerVotes(10);
        r18.setLoserVotes(9);
        r18.setEndDate(time);
        r18.setStartDate(time2);
        
        Round r19 = new Round();
        r19.setLoserId(4);
        r19.setWinnerId(5);
        r19.setWinnerVotes(14);
        r19.setLoserVotes(9);
        r19.setEndDate(time);
        r19.setStartDate(time2);
        
        Round r20 = new Round();
        r20.setLoserId(9);
        r20.setWinnerId(2);
        r20.setWinnerVotes(12);
        r20.setLoserVotes(3);
        r20.setEndDate(time);
        r20.setStartDate(time2);
        

        dao.createUserStatus(us);
        dao.createUserStatus(us2);
        dao.createUserStatus(us3);
        dao.createUserStatus(us4);
        dao.createUserStatus(us5);
        dao.createUser(dummy);
        dao.createUser(u);
        dao.createUser(u2);
        dao.createUser(u3);
        dao.createUser(u4);
        dao.createUser(u5);
        dao.createUser(u6);
        dao.createUser(u7);
        dao.createUser(u8);
        dao.createRound(r);
        dao.createRound(r2);
        dao.createRound(r3);
        dao.createRound(r4);
        dao.createRound(r5);
        dao.createRound(r6);
        dao.createRound(r7);
        dao.createRound(r8);
        dao.createRound(r9);
        dao.createRound(r10);
        dao.createRound(r11);
        dao.createRound(r12);
        dao.createRound(r13);
        dao.createRound(r14);
        dao.createRound(r15);
        dao.createRound(r16);
        dao.createRound(r17);
        dao.createRound(r18);
        dao.createRound(r19);
        dao.createRound(r20);


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
        System.out.println(u4.toString());
        System.out.println(u5.toString());
        System.out.println(u6.toString());
        System.out.println(u7.toString());
        System.out.println(u8.toString());
        System.out.println("\n" + r.toString());
        System.out.println(r2.toString());
        System.out.println(r3.toString());
        System.out.println(r4.toString());
        System.out.println(r5.toString());
        System.out.println(r6.toString());
        System.out.println(r7.toString());
        System.out.println(r8.toString());
        System.out.println(r9.toString());
        System.out.println(r10.toString());
        System.out.println(r11.toString());
        System.out.println(r12.toString());
        System.out.println(r13.toString());
        System.out.println(r14.toString());
        System.out.println(r15.toString());
        System.out.println(r16.toString());
        System.out.println(r17.toString());
        System.out.println(r18.toString());
        System.out.println(r19.toString());
        System.out.println(r20.toString());
        System.out.println("\n\n");
        return "home";
    }
}
