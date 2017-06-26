package chat.chickentalk.controllers;

import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SpotlightController {
    private SpotlightService svc = SpotlightService.getInstance();

    @ResponseBody @RequestMapping(value = "/spotlight/getQueue", method = RequestMethod.GET)
    public List<String> getSpotlightQueue() {

        return svc.getSpotlightQueue();
    }

    @ResponseBody
    @RequestMapping(value = "/addUserToQueue", method = RequestMethod.POST)
    public boolean addUserToQueue(
            HttpServletRequest request
    ) {

        User u = (User) request.getSession().getAttribute("user");

        return svc.addUserToQueue(u);
    }

    @ResponseBody
    @RequestMapping(value = "/removeUserFromQueue", method = RequestMethod.POST)
    public boolean removeUserFromQueue(
            HttpServletRequest request
    ) {

        User u = (User) request.getSession().getAttribute("user");

        return svc.removeUserFromQueue(u);
    }

}
