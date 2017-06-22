package chat.chickentalk.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;
import chat.chickentalk.util.ConnectionUtil;

public class DaoImpl implements Dao {
    private static DaoImpl INSTANCE = null;

    private DaoImpl() { }

    public static DaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoImpl();
        }

        return INSTANCE;
    }

    public boolean createUser(User p) { // CREATE: returns true on success, false on exception
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.save(p);
			ta.commit();
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}
    
    public User getUserById(int id) { // READ: returns User with input ID.
        Session s = ConnectionUtil.getSession();
        User u = null;

        try {
            u = (User) s.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

        return u;
    }
    
    public boolean updateUser(User u) { // UPDATE: returns true if User updated successfully.
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.update(u);
			ta.commit();
			
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}
    
	public boolean deleteUser(User u) { 			   // DELETE: returns true if User was
		Session session = ConnectionUtil.getSession(); // successfully removed from the DB.
		
		try{
			Transaction ta = session.beginTransaction();
			session.delete(u);
			ta.commit();
			
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}
	
    public boolean createRound(Round r) { // CREATE: returns true on success, false on exception
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.save(r);
			ta.commit();
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}
    
    public Round getRoundById(int id) { // READ: returns User with input ID.
        Session s = ConnectionUtil.getSession();
        Round r = null;

        try {
            r = (Round) s.get(Round.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

        return r;
    }
    
    public boolean updateRound(Round u) { // UPDATE: returns true if User updated successfully.
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.update(u);
			ta.commit();
			
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}
    
	public boolean deleteRound(Round u) { 			   // DELETE: returns true if User was
		Session session = ConnectionUtil.getSession(); // successfully removed from the DB.
		
		try{
			Transaction ta = session.beginTransaction();
			session.delete(u);
			ta.commit();
			
			return true;
		} 
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			session.close();
		}
	}

	public ArrayList<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Round> getAllRounds() {
		// TODO Auto-generated method stub
		return null;
	}

    public Person getPersonById(int id) { // READ: PERSON TEST CLASS
        Session s = ConnectionUtil.getSession();
        Person p = null;

        try {
            p = (Person) s.get(Person.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

        return p;
    }
	
}
