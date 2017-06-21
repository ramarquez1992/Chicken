package chat.chickentalk.dao;

import java.util.ArrayList;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;

public interface Dao {

	public Person getPersonById(int id); // testing
	
	// User Methods
    User getUserById(int id);
    ArrayList<User> getAllUsers();
    
    // Round Methods
    Round getRoundById(int id);
    ArrayList<Round> getAllRounds();
    
    // Join Methods
    
}
