package chat.chickentalk.dao;

import java.util.ArrayList;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;

public interface Dao {

	public Person getPersonById(int id); // testing
	
	// User Methods
	boolean createUser(User u);
    User getUserById(int id);
	boolean updateUser(User u);
	boolean deleteUser(User u);
    ArrayList<User> getAllUsers();
    
    // Round Methods
    boolean createRound(Round u);
    Round getRoundById(int id);
	boolean updateRound(Round u);
	boolean deleteRound(Round u);
    ArrayList<Round> getAllRounds();
    
    // Join Methods
    
}
