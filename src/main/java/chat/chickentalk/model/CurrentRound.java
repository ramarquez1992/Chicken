package chat.chickentalk.model;

import java.util.Deque;

public class CurrentRound {
    int id;
    User chick1;
    User chick2;
    int chick1Votes;
    int chick2Votes;
    Deque<User> queue;
    boolean chick1Ready = false;
    boolean chick2Ready = false;
    boolean started = false;
    boolean finished = false;
    long secondsRemaining;


    public CurrentRound(User chick1, User chick2, int chick1Votes, int chick2Votes,
                        Deque<User> queue, boolean chick1Ready, boolean chick2Ready,
                        boolean started, boolean finished, int id, long secondsRemaining) {
        this.chick1 = chick1;
        this.chick2 = chick2;
        this.chick1Votes = chick1Votes;
        this.chick2Votes = chick2Votes;
        this.queue = queue;
        this.chick1Ready = chick1Ready;
        this.chick2Ready = chick2Ready;
        this.started = started;
        this.finished = finished;
        this.id = id;
        this.secondsRemaining = secondsRemaining;
    }

    public boolean isFinished() {
        return finished;
    }

    public long getSecondsRemaining() {
        return secondsRemaining;
    }

    public void setSecondsRemaining(long secondsRemaining) {
        this.secondsRemaining = secondsRemaining;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isChick1Ready() {
        return chick1Ready;
    }

    public void setChick1Ready(boolean chick1Ready) {
        this.chick1Ready = chick1Ready;
    }

    public boolean isChick2Ready() {
        return chick2Ready;
    }

    public void setChick2Ready(boolean chick2Ready) {
        this.chick2Ready = chick2Ready;
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
