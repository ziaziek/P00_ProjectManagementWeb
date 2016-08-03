/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Users;
import javax.annotation.Resource;

/**
 *
 * @author Przemo
 */
public class UserManagementService {
    
    @Resource
    MailService mailService;
    
    public int registerUser(final Users user, final String password){
        //TODO: password needs to be encrypted!!!!
        //save information and send confirmation email
        HibernateUtil.runSQLQuery("select pr_create_user("+user.getEmail()+","+user.getRole().getId()+","+password);
        mailService.sendAccountConfirmationEmail(user.getEmail());
        return -1;
    }
}
