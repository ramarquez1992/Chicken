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

    public boolean start() {
        started = true;
        spotlightTimerTask = new TimerTask() {
            @Override
            public void run() {
                svc.stopRound();
                svc.startNextRound();

                sendRound(svc.getCurrentRound());
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(spotlightTimerTask, new Date(), TimeUnit.MILLISECONDS.convert(svc.getRoundLength(), TimeUnit.SECONDS)); // Starts automatically

        return true;
    }

    public void stop() {
        started = false;

        svc.stopRound();
        spotlightTimerTask.cancel();
        spotlightTimerTask = null;
    }

    public void addActiveUser(String sessionId, String email) {
        svc.addActiveUser(sessionId, email);

        if (!started && svc.getSpotlightQueue().size() > 1) {
            start();
        }

        sendRound(getCurrentRound());
    }

    public void removeActiveUser(String sessionId) {
        User au = svc.getActiveUser(sessionId);
        boolean currentlyPlaying = false;

        if (au.getId() == svc.getChick1().getId() || au.getId() == svc.getChick2().getId()) {
            currentlyPlaying = true;
        }

        if (currentlyPlaying || svc.getSpotlightQueue().size() <= 2) {
            stop();
            svc.removeActiveUser(sessionId);

            if (svc.getSpotlightQueue().size() > 1) {
                start();
            }
        }

        sendRound(getCurrentRound());
    }

}

