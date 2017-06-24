package chat.chickentalk.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import chat.chickentalk.model.User;
import chat.chickentalk.service.UserService;

/**
 * Servlet mappings: /LoginServlet, /login
 * 
 * Servlet logs a User in and redirects them to the landing page. 
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}


	/**
	 * Retrieves the entered email and password from the login form and compares them
	 * against every User's login credentials in the database. Redirects them to 
	 * the main page if success, landing page if failure. 
	 * 
	 * Login form takes: email, password 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession(true); 
		session.setAttribute("user", "-");
		
        String email = request.getParameter("email"); 
        String password = request.getParameter("password"); 
                
        UserService service = new UserService(); 
        
        // check individually for matching email and password set from all Users
        // unless we want to add method for individual User retrieval in dao 
        for(User user : service.getAllUsers()){
        	if(email.equals(user.getEmail()) && password.equals(user.getPassword())){
        		session.setAttribute("user", user);
        		//not 100% sure which one we would want to use
//        		request.getRequestDispatcher("main.jsp").forward(request, response);
        		response.sendRedirect("main.jsp");
        		break; 
        	}
        }
        request.getRequestDispatcher("landing.jsp").forward(request, response);
	}
}
