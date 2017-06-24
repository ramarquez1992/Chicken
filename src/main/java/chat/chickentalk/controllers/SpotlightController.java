package chat.chickentalk.controllers;

import chat.chickentalk.service.SpotlightService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpotlightController {

    @ResponseBody @RequestMapping(value = "/spotlight/getQueue", method = RequestMethod.GET)
    public List<String> getSpotlightQueue() {

        return SpotlightService.getInstance().getSpotlightQueue();
    }

}
