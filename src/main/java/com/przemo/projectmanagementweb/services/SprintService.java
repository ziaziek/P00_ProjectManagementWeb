/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Sprint;
import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class SprintService {
    
    public List<Sprint> retrieveAllSprints(){
        return HibernateUtil.runQuery("from Sprint");
    }
    
    public Sprint getSprint(final int id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        return (Sprint) s.load(Sprint.class, id);
    }
}
