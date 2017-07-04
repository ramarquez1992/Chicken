package chat.chickentalk.controllers;

import chat.chickentalk.model.CurrentRound;
import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class SpotlightController {
    @Autowired
    private SpotlightService svc;

    @Autowired
    private SimpMessagingTemplate template;

    private boolean started = false;
    private Timer timer;
    private TimerTask spotlightTimerTask;

    @ResponseBody
    @RequestMapping(value = "/spotlight/forceUpdate", method = RequestMethod.GET)
    public void forceUpdate() {
        sendRound(svc.getCurrentRound());
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/addSelfToQueue", method = RequestMethod.POST)
    public boolean addSelfToQueue(HttpServletRequest request) {

        User u = (User) request.getSession().getAttribute("user");
        boolean result = svc.addUserToQueue(u);

        if (result) {
            sendRound(svc.getCurrentRound());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/removeSelfFromQueue", method = RequestMethod.POST)
    public boolean removeSelfFromQueue(HttpServletRequest request) {

        User u = (User) request.getSession().getAttribute("user");
        boolean result = svc.removeUserFromQueue(u);

        if (result) {
            sendRound(svc.getCurrentRound());
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/voteChick1", method = RequestMethod.GET)
    public int voteChick1() {
        int votes = svc.voteChick1();
        sendRound(svc.getCurrentRound());

        return votes;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/voteChick2", method = RequestMethod.GET)
    public int voteChick2() {
        int votes = svc.voteChick2();
        sendRound(svc.getCurrentRound());

        return votes;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/getCurrentRound", method = RequestMethod.GET)
    public CurrentRound getCurrentRound() {
        return svc.getCurrentRound();
    }

    public void sendRound(CurrentRound cr) {
        this.template.convertAndSend("/topic/messages", cr);
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/setChick1Ready", method = RequestMethod.GET)
    public void setChick1Ready() {
        svc.setChick1Ready(true);

        if (svc.isChick1Ready() && svc.isChick2Ready() && !started) {
            start();
        } else {
            sendRound(svc.getCurrentRound());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/setChick2Ready", method = RequestMethod.GET)
    public void setChick2Ready() {
        svc.setChick2Ready(true);

        if (svc.isChick1Ready() && svc.isChick2Ready() && !started) {
            start();
        } else {
            sendRound(svc.getCurrentRound());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/setChick1Drop", method = RequestMethod.GET)
    public boolean setChick1Drop() {
        // TODO: make sure curr user is chick1
        System.out.println("11111");
        User u = svc.getChick1();

        System.out.println("2222222");
        svc.setChick1(svc.getSpotlightQueue().removeFirst());
        System.out.println("33333333");
        svc.addUserToQueue(u);
        System.out.println("44444444");

        sendRound(svc.getCurrentRound());
        System.out.println("55555555");

        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/setChick2Drop", method = RequestMethod.GET)
    public boolean setChick2Drop() {
        // TODO: make sure curr user is chick2
        User u = svc.getChick2();

        svc.setChick2(svc.getSpotlightQueue().removeFirst());
        svc.addUserToQueue(u);

        sendRound(svc.getCurrentRound());

        return true;
    }

    public boolean start() {
        svc.setStarted(true);
        started = true;

        svc.startNextRound();
        sendRound(svc.getCurrentRound());

        spotlightTimerTask = new TimerTask() {
            @Override
            public void run() {
                started = false;
                svc.stopRound();

                //TODO: create next round instead of start
                if (svc.getSpotlightQueue().size() > 1) {
                    svc.createNextRound();
                }

                sendRound(svc.getCurrentRound());
            }
        };

        timer = new Timer();
        timer.schedule(spotlightTimerTask, TimeUnit.MILLISECONDS.convert(svc.getRoundLength(), TimeUnit.SECONDS));

        return started;
    }

    public void stop() {
        started = false;

        svc.stopRound();
        if (spotlightTimerTask != null) spotlightTimerTask.cancel();
        spotlightTimerTask = null;
    }

    public void addActiveUser(String sessionId, String email) {
        svc.addActiveUser(sessionId, email);

        if (!started && svc.getSpotlightQueue().size() > 1) {
            svc.createNextRound();
        }

        sendRound(getCurrentRound());
    }

    public void removeActiveUser(String sessionId) {
        User au = svc.getActiveUser(sessionId);
        boolean currentlyPlaying = false;

        if (
                (svc.getChick1() != null && au.getId() == svc.getChick1().getId()) ||
                (svc.getChick2() != null && au.getId() == svc.getChick2().getId())
            ) {
            currentlyPlaying = true;
            if (au.getId() == svc.getChick1().getId()) svc.setChick1(null);
            if (au.getId() == svc.getChick2().getId()) svc.setChick2(null);
        }


        if (currentlyPlaying || svc.getSpotlightQueue().size() <= 2) {
            stop();
            svc.removeActiveUser(sessionId);

            if (svc.getSpotlightQueue().size() > 1) {
                svc.createNextRound();
            }
        }

        sendRound(getCurrentRound());
    }

}

