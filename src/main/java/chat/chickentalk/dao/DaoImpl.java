package chat.chickentalk.dao;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.pojos.Round;
import chat.chickentalk.pojos.User;
import chat.chickentalk.util.ConnectionUtil;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DaoImpl implements Dao {
    private static DaoImpl INSTANCE = null;

    private DaoImpl() { }

    public static DaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DaoImpl();
        }

        return INSTANCE;
    }

    public Person getPersonById(int id) {
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

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Round getRoundById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Round> getAllRounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
