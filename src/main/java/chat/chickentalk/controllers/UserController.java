package chat.chickentalk.controllers;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private UserService svc = UserService.getInstance();

    @ResponseBody
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) {
        User u = svc.getUserById(userId);

        return u;
    }

    /**
     * Retrieves User of the current session and the input from the form.
     * Response will return a JSON string of User's new information if success -
     * {email: " ", password: " ", firstname: " ", lastname:" ", avatar:" ", isBaby:" "}.
     * {result:"false"} otherwise
     *
     * Form Parameters: firstname, lastname, email, bebechick, password, password-check, avatar
     */
    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public User updateUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("passwordCheck") String passwordCheck,
            @RequestParam("isBaby") boolean isBaby,
            @RequestParam("avatar") String avatar,
            @RequestParam("status") String status,
            HttpServletRequest request
            ) {

        User u = (User) request.getSession().getAttribute("user");

        boolean result = svc.updateUser(u, firstName, lastName,
                email, isBaby, password, passwordCheck, avatar);

        return (result ? u : null);
    }
}
