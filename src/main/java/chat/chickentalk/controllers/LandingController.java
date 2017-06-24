package chat.chickentalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LandingController {

    @RequestMapping(value = { "", "/", "/landing" }, method = RequestMethod.GET)
    public String getLanding() {
        System.out.println("GET landing");
        return "landing";
    }

}
