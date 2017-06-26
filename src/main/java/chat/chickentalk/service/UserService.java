package chat.chickentalk.service;

import java.util.List;

import chat.chickentalk.dao.Dao;
import chat.chickentalk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    Dao dao;

	/**
	 * Receives information sent from the registration servlet and creates User
	 * object to pass to dao. Will not create User if email already exists in
	 * database.
	 * 
	 * @param firstname
	 *            new User's firstname
	 * @param lastname
	 *            new User's lastname
	 * @param email
	 *            new User's <i>unique</i> email address
	 * @param password
	 *            new User's password
	 * @return true if dao adds new User to database successfully, false
	 *         otherwise
	 */
	public boolean createUser(String firstname, String lastname, String email, String password) {
		List<User> users = dao.getAllUsers();
		for (User u : users) {
			if (email.equals(u.getEmail()))
				return false;
		}

		User user = new User();
		user.setFirstName(firstname);
		user.setLastName(lastname);
		user.setEmail(email);
		user.setPassword(password);

		if (dao.createUser(user))
			return true;
		else
			return false;
	}

	/**
	 * Receives the User of the current session and the (new) information from
	 * the Account Settings page. Will update only if new email is still unique
	 * and the passwords match.
	 * 
	 * @param user
	 *            User of current session
	 * @param firstname
	 *            new firstname to update for User
	 * @param lastname
	 *            new lastname to update for User
	 * @param email
	 *            new email to update for User
	 * @param isBaby
	 *            maturity filter, on for true, off for false
	 * @param password
	 *            new password to update for User
	 * @param passwordCheck
	 *            second password the user must enter to ensure passwords match
	 * @param Avatar
	 *            User's avatar image
	 * @return true if new User information is successfully updated by dao,
	 *         false otherwise
	 */
	public boolean updateUser(User user, String firstname, String lastname, String email, boolean isBaby,
			String password, String passwordCheck, String Avatar) {
		List<User> users = dao.getAllUsers();
		for (User u : users) {
			if (email.equals(u.getEmail())) // check that email not already
											// registered in db
				return false;
		}
		if (!password.equals(passwordCheck) & password != null & passwordCheck != null)
			return false;
		// pw check will exist unless front end checks for matching passwords
		// may need to tweak password check later

		// if any parameter is empty, keep old name to prevent overwriting empty
		// values to db
		/// else if parameter is not empty, set User info to new parameters
		if (!firstname.equals(""))
			user.setFirstName(firstname);
		if (!lastname.equals(""))
			user.setLastName(lastname);
		if (!email.equals(""))
			user.setEmail(email);
		if (!password.equals(""))
			user.setPassword(password);

		user.setBaby(isBaby);
		user.setAvatar(Avatar);

		if (dao.updateUser(user))
			return true;
		else
			return false;
	}

	/**
	 * Deletes the User's account.
	 * 
	 * @param user
	 *            the User of the current session
	 * @return true if dao successfully deletes the User, false otherwise
	 */
	public boolean deleteUser(User user) {
		if (dao.deleteUser(user))
			return true;
		else
			return false;
	}

	/**
	 * Returns a List<User> of all Users stored in database.
	 * 
	 * @return List<User>
	 */
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	public User getUserById(int id) {
		return dao.getUserById(id);
	}

	public User getUserByEmail(String email){
		return dao.getUserByEmail(email);
	}
}
