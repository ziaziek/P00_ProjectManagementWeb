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
import org.slf4j.LoggerFactory;

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
     *
     * @param qry
     * @return list of objects
     */
    public static List runQuery(final String qry) {
        Session s = getSessionFactory().openSession();
        List l = s.createQuery(qry).list();
        s.close();
        return l;
    }

    public static List runSQLQuery(final String qry){
        Session s = getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        List l = null;
        try {
           l = s.createSQLQuery(qry).list(); 
           tx.commit();
        } catch(Exception ex){
           tx.rollback();
            LoggerFactory.getLogger("HibernateUtils").error(ex.getMessage());
        }
        s.close();
        return l;
    }
    
    /**
     * Saves or updates object
     *
     * @param obj
     */
    public static void saveObject(Object obj) {
        doTransactionalOperation(obj, OPERATION.SAVE);
    }
    
    public static void deleteObject(Object obj) {
        doTransactionalOperation(obj, OPERATION.DELETE);
        
    }
    
    private static void doTransactionalOperation(Object obj, OPERATION op) {
        if (obj != null && op != null) {
            Session s = getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = s.beginTransaction();
                switch (op) {
                    case SAVE:
                        s.saveOrUpdate(obj);
                        break;
                    case DELETE:
                        s.delete(obj);
                        break;
                }
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

    private enum OPERATION {
        SAVE, DELETE, SELECT, EXECUTE
    };
}
