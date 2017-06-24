package chat.chickentalk.controllers;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) {
        User u = DaoImpl.getInstance().getUserById(userId);

        return u;
    }

}
