package chat.chickentalk.controllers;


import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import chat.chickentalk.service.UserService;
import chat.chickentalk.util.PasswordStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.Base64;

import chat.chickentalk.model.User;
import chat.chickentalk.service.SpotlightService;
import chat.chickentalk.service.UserService;

@Controller
public class UserController {


    @Autowired
    private UserService svc;
    
	@Value("#{systemEnvironment['CHICKEN_AWS_ACCESS_KEY_ID']}")
	private String accessKey; 
	
	@Value("#{systemEnvironment['CHICKEN_AWS_SECRET_ACCESS_KEY']}")
	private String secretKey; 
	
	@Value("#{systemEnvironment['CHICKEN_BUCKET_NAME']}")
	private String bucketName; 

    @Autowired
    private SpotlightService spotlightService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String getProfile() {
        return "profile";
    }
    
    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String get403(){
    	return "403"; 
    }

    @ResponseBody
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable int userId) {
        User u = svc.getUserById(userId);

        return u;
    }

    /**
     * Gets the session's user object and refreshes the information it contains before
     * returning the User object.
     *
     */
    @ResponseBody
    @RequestMapping(value = "/users/getSelf", method = RequestMethod.GET)
    public User getSelf(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        u = svc.getUserById(u.getId());
        request.getSession().setAttribute("user", u);
        return u;
    }
    
    /**
     * TODO: documentation 
     *
     * Form Parameters: 
     */
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String updateUser(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "passwordCheck", required = false) String passwordCheck,
			@RequestParam(value = "isBaby", defaultValue = "false") boolean isBaby,
			@RequestParam(value = "status", defaultValue = "normal") String status, HttpServletRequest request) {

		User user = (User) request.getSession().getAttribute("user");
		String emailTemp = (email.equals("")) ? user.getEmail() : email;
		
//		TODO MAYBE: instantiate aws credentials and client elsewhere as bean 
		
//		authenticate credentials 
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//		connect to s3 client
		AmazonS3Client s3client = new AmazonS3Client(credentials); 
		
		String filename = Integer.toString(user.getId());	
		
		String avatar =  s3client.getUrl(bucketName, filename).toString();
		
		boolean result = svc.updateUser(user, firstName, lastName, email,isBaby, password, passwordCheck, avatar, status);

		user = result ? svc.getUserByEmail(emailTemp) : null;
		request.getSession().setAttribute("user", user);
		
		request.getSession().setAttribute("successMsg", "Profile successfully updated!");
		return "profile";
	}
	
	/**
     * Updating user information from ajax call.
     * 
     * Form Parameters: firstname, lastname, email, bebechick, password, password-check, avatar
     */
	@ResponseBody @RequestMapping(value = "/updateProfileAjax/{userId}/{status}", method = RequestMethod.GET)
	public boolean updateUserAjax(@PathVariable int userId, @PathVariable String status){
		boolean result = svc.updateStatus(userId, status);
		return result;
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
        	boolean correctPassword = PasswordStorage.verifyPassword(password, user.getPassword());
        	
        	
            if (user != null && correctPassword) {
                session.setAttribute("user", user);
                return "home";
            } else {
                map.addAttribute("errorMsg", "Your login information was incorrect. Please try again.");
                return "landing";
            }
        } catch(Exception e){
        	map.addAttribute("errorMsg", "Your login information was incorrect. Please try again.");
            return "landing";
        }
    }

    /**
     * Ends the current Session and redirects to the landing page.
     *
     * @param request HttpServletRequest
     */
    @RequestMapping(value = "/logoutUser", method = RequestMethod.GET)
    public String logoutUser(HttpServletRequest request) {
        User u = (User) request.getSession().getAttribute("user");
        spotlightService.removeUserFromQueue(u);

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
            HttpServletResponse response)
    {
        boolean result = svc.createUser(firstName, lastName, email, password);
        User user = svc.getUserByEmail(email);
        request.getSession().setAttribute("user", user);

        try {
            if (result) {
            	request.getSession().setAttribute("successMsg", "Account Registration complete! You may log-in at any time.");
                response.sendRedirect("home");
            } else {
            	request.getSession().setAttribute("errorMsg", "Account Registration failed: Invalid information entered.");
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

	/**
	 * Retrievs the base64 String representation of the uploaded image and uploads it to 
	 * AWS S3 Bucket. Then sets it for the current Session's attribute. 
	 *  TODO: documetation stuff
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	public String uploadAvatar(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
				
//		the base64 String representation of the uploaded avatar image
//		split needed to skip over the "data:image/png;base64," from html src
		String avatar = request.getParameter("avatar").split(",")[1]; 

//	debugging	System.out.println("\n\n\n AVATAR " + avatar + "\n\n\n");		
		
//		TODO: instantiate aws credentials and client elsewhere as bean 		
//		change this credential setup later to be more secure 
//		authenticate credentials 
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//		connect to s3 client
		AmazonS3Client s3client = new AmazonS3Client(credentials); 
		
		String filename = Integer.toString(user.getId());	
		
		//decode b64 string into byte array 
		byte[] imgByteArray = Base64.decode(avatar); 		
		
		// create metadata for the object to be put in s3  
		ObjectMetadata metadata = new ObjectMetadata(); 
		long length = imgByteArray.length; 	// SET OBJECT LENGTH PLEASE 
		metadata.setContentLength(length);
		
//		public PutObjectRequest(String bucketName, String key, InputStream input, ObjectMetadata metadata); 
		s3client.putObject(new PutObjectRequest(bucketName, filename,
				new ByteArrayInputStream(imgByteArray), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		

		user.setAvatar(s3client.getUrl(bucketName, filename).toString());

		
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("avatar", s3client.getUrl(bucketName, filename));
		return "profile";
	}
}
