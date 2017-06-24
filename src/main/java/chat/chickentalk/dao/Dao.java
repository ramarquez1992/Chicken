package chat.chickentalk.dao;

import java.util.List;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.model.UserStatus;

public interface Dao {

    // Status Methods
	public UserStatus getUserStatus(int id);
	public int setUserStatus(int id);
	
    // User Methods
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean createUser(User u);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    User getUserById(int id);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean updateUser(User u);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean deleteUser(User u);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    List<User> getAllUsers();

    // Round Methods
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean createRound(Round u);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Round getRoundById(int id);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean updateRound(Round u);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean deleteRound(Round u);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    List<Round> getAllRounds();

    // Join Methods

}
