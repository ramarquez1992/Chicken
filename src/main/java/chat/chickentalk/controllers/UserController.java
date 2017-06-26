package chat.chickentalk.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;

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
	 * Retrieves User of the current Session and updates their info with the
	 * given parameters. Returns the updated User if change is successful, null
	 * otherwise.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param passwordCheck
	 *            second password prompt to ensure password is correct
	 * @param isBaby
	 *            maturity filter toggle
	 * @param avatar
	 * @param status
	 *            current account standing
	 * @param request
	 *            HttpServletRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public User updateUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("passwordCheck") String passwordCheck, @RequestParam("isBaby") boolean isBaby,
			@RequestParam("avatar") String avatar, @RequestParam("status") String status, HttpServletRequest request) {

		User u = (User) request.getSession().getAttribute("user");

		boolean result = svc.updateUser(u, firstName, lastName, email, isBaby, password, passwordCheck, avatar);

		return (result ? u : null);
	}

	/**
	 * Takes the email and password paramters and checks if a User with that
	 * email/password set exists. Sets User to current session and returns User.
	 * Redirects to home page if the User exists.
	 * 
	 * @param email
	 * @param password
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public User loginUser(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		User user = svc.getUserByEmail(email);
		request.getSession().setAttribute("user", user);
		try {
			if (user.getStatus().getName().equals("permanent ban"))
				response.sendRedirect("landing");
			if (user != null)
				response.sendRedirect("home");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Ends the current Session and redirects to the landing page.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("landing"); // TBD by Richie
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new User from the given paramters. If User account creation was
	 * a success, returns the new User, null otherwise. Then redirects to the
	 * home page.
	 * 
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public User createUser(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = svc.createUser(firstname, lastname, email, password);
		User user = svc.getUserByEmail(email);
		request.getSession().setAttribute("user", user);
		try {
			response.sendRedirect("home");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (result ? user : null);
	}

	/**
	 * Deletes the User account of the current Session and logouts the User if
	 * success.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		if (svc.deleteUser(user))
			logoutUser(request, response);
		// what do if deletion fails??
	}

	/**
	 * Gets the User of the current Session and changes the account status of
	 * the User with the provided email. Returns true if it works, false
	 * otherwise.
	 * 
	 * @param email
	 *            of the User whose status is to be changed
	 * @param status
	 *            the id of the status change (0 = "normal", 1 = "shadown ban",
	 *            2 = "permanent ban", 3 = "admin", 4 = "Chicken")
	 * @param request
	 *            HttpServletRequest
	 * @return true if the User account's status was changed, false otherwise.
	 */
	@ResponseBody
	@RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
	public boolean changeUserStatus(@RequestParam("email") String email, @RequestParam("status") int status,
			HttpServletRequest request) {
		User user = (User) request.getAttribute("user");

		if (svc.changeUserStatus(user, email, status))
			return true;
		else
			return false;
	}
}
