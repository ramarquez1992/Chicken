package chat.chickentalk.controllers;

import chat.chickentalk.dao.DaoImpl;
import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public void loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response){
        User user = svc.getUserByEmail(email);
        request.getSession().setAttribute("user", user);
        try {
            if (user != null && user.getPassword().equals(password)) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect("landing");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Ends the current Session and redirects to the landing page.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @ResponseBody
    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    public void logoutUser(
            HttpServletRequest request,
            HttpServletResponse response){
        request.getSession().invalidate();
        try{
            response.sendRedirect("landing");	//TBD by Richie
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
    public void deleteUser(
            HttpServletRequest request,
            HttpServletResponse response)
    {
        User user = (User)request.getSession().getAttribute("user");
        if(svc.deleteUser(user))
            logoutUser(request, response);
        //what do if deletion fails??
    }

}
