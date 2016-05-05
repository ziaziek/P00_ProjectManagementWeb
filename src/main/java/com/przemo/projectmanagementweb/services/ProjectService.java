/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.HibernateUtil;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class ProjectService {
    
    public List<Projects> getAllProjects(){
        return HibernateUtil.runQuery("from Projects");
    }
}
