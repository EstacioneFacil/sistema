package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration().configure("/config/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
    
    public void close() {
        if (getSession() != null && getSession().isOpen()) {
            getSession().close();
        }
    }
}
