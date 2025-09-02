package util;

import entities.Certificate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {

        try{
            if(sessionFactory ==  null){

                sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Certificate.class).buildSessionFactory();

            }
        }

        catch (Exception e){
            throw new RuntimeException("error in creating sessions factory "+ e.getMessage());
        }

    }


    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }
}
