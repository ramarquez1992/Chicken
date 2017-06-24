package chat.chickentalk.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;

/**
 * Servlet mappings: /UpdateAccountServlet, /update
 * 
 * Servlet updates User's account information. 
 */
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}


	/**
	 * Retrieves User of the current session and the input from the form. 
	 * Response will return a JSON string of User's new information if success - 
	 * {email: " ", password: " ", firstname: " ", lastname:" ", avatar:" ", isBaby:" "}. 
	 * {result:"false"} otherwise 
	 * 
	 * Form Parameters: firstname, lastname, email, bebechick, password, password-check, avatar
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession(true); 
		User user = (User)session.getAttribute("user"); 
		UserService service = new UserService(); 
		PrintWriter writer = response.getWriter();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname"); 
		String email = request.getParameter("email"); 
		boolean isBaby = Boolean.parseBoolean(request.getParameter("bebechick"));
		String password = request.getParameter("password"); 
		String passwordCheck = request.getParameter("password-check"); 
		String Avatar = request.getParameter("avatar"); 
		
		String json = "";
		
		if(service.updateUser(user, firstname, lastname, 
				email, isBaby, password, passwordCheck, Avatar)){			
			// {email: "", password: "", firstname: "", lastname:"", avatar:"", isBaby:""}
			json += "{email:\"" + email + "\",password:\"" + password + "\",firstname:\""
					+ firstname + "\",lastname:\"" + lastname + "\",avatar:\"" +
					Avatar + "\",isBaby:\"" + isBaby + "\"}"; 
	        writer.write(json);
		}
		else{
			json += "{result:\"false\"}"; 
			writer.write(json);
		}
	}

}
