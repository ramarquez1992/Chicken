package chat.chickentalk.controllers;

import chat.chickentalk.model.CurrentRound;
import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.util.resources.cldr.ebu.CurrencyNames_ebu;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class SpotlightController {
    @Autowired
    private SpotlightService svc;

    @ResponseBody @RequestMapping(value = "/spotlight/getQueue", method = RequestMethod.GET)
    public Deque<User> getSpotlightQueue() {

        return svc.getSpotlightQueue();
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
    @RequestMapping(value = "/spotlight/getChick1", method = RequestMethod.GET)
    public User getChick1(HttpServletRequest request) {
        User chick1 = svc.getChick1();
        User currUser = (User) request.getSession().getAttribute("user");

        if (chick1.getId() == currUser.getId()) chick1 = null;

        return chick1;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/getChick2", method = RequestMethod.GET)
    public User getChick2(HttpServletRequest request) {
        User chick2 = svc.getChick2();
        User currUser = (User) request.getSession().getAttribute("user");

        if (chick2.getId() == currUser.getId()) chick2 = null;

        return chick2;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/stopRound", method = RequestMethod.GET)
    public Round stopRound() {
        return svc.stopRound();
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/startNextRound", method = RequestMethod.GET)
    public boolean startNextRound() {
        svc.startNextRound();
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/voteChick1", method = RequestMethod.GET)
    public int voteChick1() {
        return svc.voteChick1();
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/voteChick2", method = RequestMethod.GET)
    public int voteChick2() {
        return svc.voteChick2();
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/getCurrentRound", method = RequestMethod.GET)
    public CurrentRound getCurrentRound() {
        return svc.getCurrentRound();
    }

//    @MessageMapping("/topic/messages")
//    @SendTo("/topic/messages")
//    public String send(@DestinationVariable("topic") String topic, String message) throws Exception {
//        System.out.println("sending " + message + " @ " + topic);
//        return message;
//    }


    @Autowired
    private SimpMessagingTemplate template;

    private int roundLength = 3;
    private Timer timer;

    public void sendRound(CurrentRound cr) {
        this.template.convertAndSend("/topic/messages", cr);
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/start", method = RequestMethod.GET)
    public boolean start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                svc.stopRound();
                svc.startNextRound();

                sendRound(svc.getCurrentRound());
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, new Date(), TimeUnit.MILLISECONDS.convert(roundLength, TimeUnit.SECONDS)); // Starts automatically


        return true;
    }

}

