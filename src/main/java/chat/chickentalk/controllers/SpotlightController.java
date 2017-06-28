package chat.chickentalk.controllers;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.Deque;
import java.util.List;

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

        return svc.addUserToQueue(u);
    }

    @ResponseBody
    @RequestMapping(value = "/spotlight/removeSelfFromQueue", method = RequestMethod.POST)
    public boolean removeSelfFromQueue(HttpServletRequest request) {

        User u = (User) request.getSession().getAttribute("user");

        return svc.removeUserFromQueue(u);
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

}
