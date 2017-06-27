package chat.chickentalk.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import chat.chickentalk.model.Round;
import chat.chickentalk.model.User;
import chat.chickentalk.model.UserStatus;

@Repository
@Transactional
public class DaoImpl implements Dao {
    private SessionFactory sessionFactory;

    private List<UserStatus> StatusList;

    public DaoImpl() {}

    public DaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    /**
     * Gets all User status, returns empty list if the database is not populated.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-24
     **/
//    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    private List<UserStatus> getStatus() {
        Session s = sessionFactory.getCurrentSession();
        List<UserStatus> list = null;

        try {
            Query query = s.createQuery("from UserStatus");
            list = query.list();

            System.out.println("List Size: " + list.size());
            for(int i = 0; i < list.size(); i++){
                System.out.println("LIST: " + list.get(i).toString());
            }

        } catch (HibernateException e) {
            e.printStackTrace();
            return list = Collections.EMPTY_LIST;
        }

        return list;
    }

    /**
     * Admin user can promote another user. Returns a boolean, true, on success. False on failure.
     *
     * 0	normal
     * 1	shadow ban
     * 2	permanent ban
     * 3	admin
     * 4	Chicken
     *
     * @param email The unique email of the user that is to be promoted.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-22
     **/
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean changeUserStatus(String email, int num){
        if(StatusList == null){
            StatusList = getStatus();
        }

        try{
            User temp = getUserByEmail(email);
            UserStatus us = StatusList.get(num);
            System.out.println(us.toString());
            temp.setStatus(us);
            updateUser(temp);

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Whoops");
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
     * READ: Returns User object that contains the input email, returns null if an exception occurs.
     *
     * @author: Darrin McIntyre
     * @since 2017-06-25
     **/
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getUserByEmail(String email) {
        Session s = sessionFactory.getCurrentSession();

        try {
            Criteria criteria = s.createCriteria(User.class);
            User u = (User) criteria.add(Restrictions.eq("email", email)).uniqueResult();
            return u;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
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
    public List<User> getAllUsers() {
        Session s = sessionFactory.getCurrentSession();
        List<User> list = null;

        try {
            Query query = s.createQuery("from User");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Round> getAllRounds() {
        Session s = sessionFactory.getCurrentSession();
        List<Round> list = null;

        try {
            Query query = s.createQuery("from Round");
            list = query.list();

        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }

        return list;
    }

}