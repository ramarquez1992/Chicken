package chat.chickentalk.dao;

import chat.chickentalk.pojos.Person;
import chat.chickentalk.util.ConnectionUtil;
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

}
