package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.annotations.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;

@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
        	sessionFactory = new Configuration ().configure ().buildSessionFactory ();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
}