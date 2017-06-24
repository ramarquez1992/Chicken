package chat.chickentalk.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.model.UserStatus;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DaoImpl implements Dao {
    private static AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
    private SessionFactory sessionFactory;

//    private List<UserStatus> list = getStatus();

    public DaoImpl() {
    }

    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static Dao getInstance() {
        return appContext.getBean("dao", Dao.class);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean createUserStatus(UserStatus p) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.save(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* TODO: returns null ptr exception with new unpopulated db
     * needs to not break execution
     * return empty list?
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    private List<UserStatus> getStatus() {
        Session s = sessionFactory.getCurrentSession();
        List<UserStatus> list = null;

        try {
            Query query = s.createQuery("from UserStatus");
            list = query.list();

            for (int i = 0; i < list.size(); i++) {
                list.get(i).toString();
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }


    /**
     * CREATE: Returns true on success, false on exception
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean createUser(User p) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.save(p);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * READ: Returns User object with input ID, returns null if an exception occurs.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getUserById(int id) {
        Session s = sessionFactory.getCurrentSession();
        User u = null;

        try {
            u = (User) s.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

        return u;
    }

    /**
     * UPDATE: Returns true if User object updated successfully.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateUser(User u) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.update(u);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * DELETE: Returns true if User was successfully removed from the database.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteUser(User u) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.delete(u);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * CREATE: Returns true on success, false on exception
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean createRound(Round r) { // CREATE: returns true on success, false on exception
        Session session = sessionFactory.getCurrentSession();

        try {
            session.save(r);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * READ: Returns Round object matching the input ID. Returns a null Round object if
     * an exception occurs.
     *
     * @param id ID number of the Round object you would like to collect from the database.
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    public Round getRoundById(int id) {
        Session s = sessionFactory.getCurrentSession();
        Round r = null;

        try {
            r = (Round) s.get(Round.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

        return r;
    }

    /**
     * UPDATE: Returns true if Round updated successfully in the database.
     *
     * @param r Round to be updated.
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean updateRound(Round r) {
        Session session = sessionFactory.getCurrentSession();

        try {
//			Transaction ta = session.beginTransaction();
            session.update(r);
//			ta.commit();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * DELETE: Returns true if Round was successfully removed from the
     * database.
     *
     * @param r Round object to be deleted.
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean deleteRound(Round r) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.delete(r);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ArrayList<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public ArrayList<Round> getAllRounds() {
        Session session = sessionFactory.getCurrentSession();
        return null;
    }

}
