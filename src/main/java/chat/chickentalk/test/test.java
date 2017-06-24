package chat.chickentalk.test;

import java.util.List;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.UserStatus;
import chat.chickentalk.model.User;

public class test {

    public static void main(String[] args) {
        Dao dao = DaoImpl.getInstance();

        User u = new User();
        u.setEmail("zv@or.com");
        u.setFirstName("Qebew");
        u.setLastName("Qwertt");

        Round r = new Round();
        r.setLoserId(2);
        r.setWinnerId(1);
        r.setWinnerVotes(11);
        r.setLoserVotes(7);

        dao.createUser(u);
        dao.createRound(r);
        List<Round> rounds = dao.getAllRounds();
        List<User> users = dao.getAllUsers();
        
        
        User temp = users.get(0);
        Round round = rounds.get(0);
        
        System.out.println(round.toString());
        UserStatus us = temp.getStatus();
        
        System.out.println(us.getName());


    }

}
