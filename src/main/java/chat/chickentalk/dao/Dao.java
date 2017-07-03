package chat.chickentalk.dao;

import java.util.List;

import chat.chickentalk.model.UserStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;

public interface Dao {

    // Status Methods
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean changeUserStatus(String email, int num);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean createUserStatus(UserStatus us);
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    List<UserStatus> getStatusList();


    // User Methods

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    boolean createUser(User u);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    User getUserById(int id);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    User getUserByEmail(String email);

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

