/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Sprint;
import java.util.List;
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
        return (Sprint) HibernateUtil.runQuery("from Sprint s where s.id="+id).get(0);
    }

    public void saveSprint(Sprint object) {
        HibernateUtil.saveObject(object);
    }
}
