package chat.chickentalk.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;
import chat.chickentalk.pojos.UserStatus;
import chat.chickentalk.util.ConnectionUtil;

public class DaoImpl implements Dao {
    private static DaoImpl INSTANCE = null;
    private List<UserStatus> list = getStatus();
    
    private DaoImpl() {}

    public static DaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoImpl();
        }

        return INSTANCE;
    }

    public boolean createUserStatus(UserStatus p) { 
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
    
    private List<UserStatus> getStatus() {
    	Session s = ConnectionUtil.getSession();
        List<UserStatus> list = null;

        try {
        	Query query = s.createQuery("from UserStatus");
			list = query.getResultList();
			
			for(int i = 0; i < list.size(); i++){
				list.get(i).toString();
			}
			
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }

        return list;
	}
    
    
    /** CREATE: Returns true on success, false on exception
	*			
	*	@author: Darrin McIntyre
	*	@since 2017-06-22
	**/
    public boolean createUser(User p) { 
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
    
    /** READ: Returns User object with input ID, returns null if an exception occurs.
	*			
	*	@author: Darrin McIntyre
	*	@since 2017-06-22
	**/
    public User getUserById(int id) {
        Session s = ConnectionUtil.getSession();
        User u = null;

        try {
            u = (User) s.get(User.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }

        return u;
    }
    
    /** UPDATE: Returns true if User object updated successfully.
     * 
     *	@author: Darrin McIntyre
     *	@since 2017-06-22
	**/
    public boolean updateUser(User u) {
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
    
    /** DELETE: Returns true if User was successfully removed from the database.
     *			
     *	@author: Darrin McIntyre
     *	@since 2017-06-22
	**/
	public boolean deleteUser(User u) {
		Session session = ConnectionUtil.getSession();
		
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
	
	/** CREATE: Returns true on success, false on exception
	*			
	*	@author: Darrin McIntyre
	*	@since 2017-06-22
	**/
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
    
    /** READ: Returns Round object matching the input ID. Returns a null Round object if
     * 		an exception occurs.
	*	@param id ID number of the Round object you would like to collect from the database.	
	*		
	*	@author: Darrin McIntyre
	*	@since 2017-06-22
	**/
    public Round getRoundById(int id) {
        Session s = ConnectionUtil.getSession();
        Round r = null;

        try {
            r = (Round) s.get(Round.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            s.close();
        }

        return r;
    }
    
    /** UPDATE: Returns true if Round updated successfully in the database.
     * 	@param r Round to be updated.
     * 
     *	@author: Darrin McIntyre
     *	@since 2017-06-22
	**/
    public boolean updateRound(Round r) {
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.update(r);
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
    
    /** DELETE: Returns true if Round was successfully removed from the
     * 			database.
     *	@param r Round object to be deleted.	
     *		
     *	@author: Darrin McIntyre
     *	@since 2017-06-22
	**/
	public boolean deleteRound(Round r) {
		Session session = ConnectionUtil.getSession();
		
		try{
			Transaction ta = session.beginTransaction();
			session.delete(r);
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
