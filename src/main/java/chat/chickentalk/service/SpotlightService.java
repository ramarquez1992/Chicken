package chat.chickentalk.service;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class SpotlightService {
    @Autowired
    Dao dao;

    private Deque<User> queue = new ArrayDeque<>();

    private Round currRound;
    private int roundLength = 3; // in seconds
    private LocalDateTime roundStart;

    private User chick1;
    private User chick2;
    private int chick1Votes;
    private int chick2Votes;

    public static void main(String[] args) {
//        int min = 1;
//        int max = 10;
//
//        ss.addUserToQueue(ss.dao.getUserById(1));
//        ss.addUserToQueue(ss.dao.getUserById(3));
//        ss.addUserToQueue(ss.dao.getUserById(5));
//        ss.addUserToQueue(ss.dao.getUserById(2));
//
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                ss.startNextRound();
//                ss.chick1Votes = ThreadLocalRandom.current().nextInt(min, max + 1);
//                ss.chick2Votes = ThreadLocalRandom.current().nextInt(min, max + 1);
//                System.out.println(ss.chick1);
//                System.out.println(ss.chick1Votes);
//                System.out.println(ss.chick2);
//                System.out.println(ss.chick2Votes);
//
//            }
//        };
//
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(task, new Date(), TimeUnit.MILLISECONDS.convert(ss.roundLength, TimeUnit.SECONDS)); // Starts automatically
    }

    public User getChick1() {
        return chick1;
    }

    public void setChick1(User chick1) {
        this.chick1 = chick1;
    }

    public User getChick2() {
        return chick2;
    }

    public void setChick2(User chick2) {
        this.chick2 = chick2;
    }

    public Round stopRound() {
        // set chick1 to winner
        // TODO: check for ties
        if (chick2Votes > chick1Votes) {
            User tmpUser = chick1;
            int tmpVotes = chick1Votes;

            chick1 = chick2;
            chick1Votes = chick2Votes;

            chick2 = tmpUser;
            chick2Votes = tmpVotes;
        }

        Round r = new Round();
        r.setWinnerId(chick1.getId());
        r.setLoserId(chick2.getId());
        r.setWinnerVotes(chick1Votes);
        r.setLoserVotes(chick2Votes);
        r.setStartDate(Timestamp.valueOf(roundStart));
        r.setEndDate(Timestamp.valueOf(LocalDateTime.now()));

        // add winner to front of queue
        getSpotlightQueue().addFirst(chick1);

        chick1 = null;
        chick2 = null;

        return r;

    }

    public void startNextRound() {
        chick1 = getSpotlightQueue().removeFirst();
        chick2 = getSpotlightQueue().removeFirst();

        roundStart = LocalDateTime.now();
    }


    public Deque<User> getSpotlightQueue() {
        return queue;
    }

    // TODO: check if not banned, not already in queue, etc.
    public boolean addUserToQueue(User u) {
        if (!queue.contains(u)) {
            queue.add(u);
            return true;
        } else {
            return false;
        }

    }

    public boolean removeUserFromQueue(User userToRemove) {
        boolean result = false;

        for (User u : queue) {
            if (u.getEmail().equals(userToRemove.getEmail())) {
                queue.remove(u);
                result = true;
                break;
            }
        }

        return result;
    }

}
