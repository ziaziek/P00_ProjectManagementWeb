/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.domain;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Przemo
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure("com/przemo/projectmanagementweb/domain/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * Runs Hibernate query
     * @param qry
     * @return list of objects
     */
    public static List runQuery(final String qry) {
        Session s = getSessionFactory().openSession();
        List l = s.createQuery(qry).list();
        s.close();
        return l;
    }
    
    /**
     * Saves or updates object
     * @param obj 
     */
    public static void saveObject(Object obj){
        Session s = getSessionFactory().openSession();
        //s.merge(obj);
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            s.saveOrUpdate(obj);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            org.slf4j.LoggerFactory.getLogger("Hibernate").error(e.getMessage());
        } finally {
            s.close();
        }
    }
}
