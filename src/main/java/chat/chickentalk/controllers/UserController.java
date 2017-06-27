package chat.chickentalk.controllers;

import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService svc;

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


    /**
     * Takes the email and password paramters and checks if a User with that email/password
     * set exists. Sets User to current session and returns User.
     * Redirects to home page if the User exists.
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            ModelMap map){
        User user = svc.getUserByEmail(email);
        try {
            if (user != null && user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "home";
            } else {
                map.addAttribute("errorMsg", "Your login information was incorrect. Please try again.");
                return "landing";
            }
        } catch(Exception e){
            e.printStackTrace();
            return "landing";
        }
    }

    /**
     * Ends the current Session and redirects to the landing page.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();

        return "landing";
    }

    /**
     * Creates a new User from the given paramters. If User account creation was a
     * success, returns the new User, null otherwise. Then redirects to the home page.
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public void createUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        boolean result = svc.createUser(firstName, lastName, email, password);
        User user = svc.getUserByEmail(email);
        request.getSession().setAttribute("user", user);

        try {
            if (result) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect("landing");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Deletes the User account of the current Session and logouts the User if success.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public boolean deleteUser(
            HttpServletRequest request,
            HttpServletResponse response)
    {
        User user = (User)request.getSession().getAttribute("user");
        if(svc.deleteUser(user)) {
            return true;
//            logoutUser(request, response);
        } else {
            return false;
        }
        // TODO: what do if deletion fails??
    }

}
