package chat.chickentalk.service;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.CurrentRound;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SpotlightService {
    @Autowired
    private Dao dao;

    private Map<String, User> activeUsers = new HashMap<>();
    private Deque<User> queue = new ArrayDeque<>();
    private int roundLength = 3; // in seconds
    private LocalDateTime roundStart;
    private User chick1;
    private User chick2;
    private int chick1Votes = 0;
    private int chick2Votes = 0;

    public int getRoundLength() {
        return roundLength;
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

    public int getChick1Votes() {
        return chick1Votes;
    }

    public int voteChick1() {
        return ++chick1Votes;
    }

    public int voteChick2() {
        return ++chick2Votes;
    }

    public int getChick2Votes() {
        return chick2Votes;
    }

    public CurrentRound getCurrentRound() {
        CurrentRound cr = new CurrentRound(
                getChick1(),
                getChick2(),
                getChick1Votes(),
                getChick2Votes(),
                getSpotlightQueue()
        );

        return cr;
    }

    public void startNextRound() {
        chick1 = getSpotlightQueue().removeFirst();
        chick2 = getSpotlightQueue().removeFirst();

        roundStart = LocalDateTime.now();
    }

    public Round stopRound() {
        if (chick1 == null || chick2 == null) return null;

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
        getSpotlightQueue().addLast(chick2);

        chick1 = null;
        chick2 = null;
        chick1Votes = 0;
        chick2Votes = 0;

        return r;
    }

    public Deque<User> getSpotlightQueue() {
        return queue;
    }

    public boolean isInQueue(User u) {
        boolean result = false;

        for (User v : queue) {
            if (u.getId() == v.getId()) {
                result = true;
                break;
            }
        }

        return result;
    }

    // TODO: check if not banned
    public boolean addUserToQueue(User u) {
        if (!isInQueue(u) && (
                chick1 == null || chick2 == null ||
                        (chick1.getId() != u.getId() && chick2.getId() != u.getId())
        )) {
            queue.add(u);
            return true;
        } else {
            return false;
        }

    }

    public List<User> getActiveUsers() {
        return new ArrayList<>(activeUsers.values());
    }

    public User getActiveUser(String sessionId) {
        return activeUsers.get(sessionId);
    }

    public void addActiveUser(String sessionId, String email) {
        User u = dao.getUserByEmail(email);
        activeUsers.put(sessionId, u);
        addUserToQueue(u);
    }

    public void removeActiveUser(String sessionId) {
        User u = activeUsers.get(sessionId);

        // TODO: don't remove from queue if user has more sockets connected
        removeUserFromQueue(u);

        activeUsers.remove(sessionId);
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
