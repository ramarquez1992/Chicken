package chat.chickentalk.dao;

import java.util.ArrayList;

import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface Dao {

    // Status Methods

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
    ArrayList<User> getAllUsers();

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
    ArrayList<Round> getAllRounds();

    // Join Methods

}
