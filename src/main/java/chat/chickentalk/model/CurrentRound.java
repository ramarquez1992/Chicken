package chat.chickentalk.model;

import java.util.Deque;

public class CurrentRound {
    User chick1;
    User chick2;
    int chick1Votes;
    int chick2Votes;
    Deque<User> queue;

    public CurrentRound(User chick1, User chick2, int chick1Votes, int chick2Votes, Deque<User> queue) {
        this.chick1 = chick1;
        this.chick2 = chick2;
        this.chick1Votes = chick1Votes;
        this.chick2Votes = chick2Votes;
        this.queue = queue;
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

    public void setChick1Votes(int chick1Votes) {
        this.chick1Votes = chick1Votes;
    }

    public int getChick2Votes() {
        return chick2Votes;
    }

    public void setChick2Votes(int chick2Votes) {
        this.chick2Votes = chick2Votes;
    }

    public Deque<User> getQueue() {
        return queue;
    }

    public void setQueue(Deque<User> queue) {
        this.queue = queue;
    }
}
