/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Users;
import java.io.Serializable;
import org.apache.wicket.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class LoginService {

    private final static String USERNAME = "przemo";
    private final static String PASSWORD = "derek";
    public static final String USER_ATTRIBUTE = "user";
    
    public boolean login(final String username, final String password) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Session.get().setAttribute(USER_ATTRIBUTE, (Serializable) HibernateUtil.runQuery("from Users where id=1").get(0));
            Session.get().bind();
            return true;
        } else {
            Session.get().replaceSession();
            return false;
        }
    }
    
    public boolean isLoggedIn(){
        return Session.get().getAttribute(LoginService.USER_ATTRIBUTE)!=null;
    }
    
    public Users getLoggedInUser(){
        if(isLoggedIn()){
            return (Users) Session.get().getAttribute(USER_ATTRIBUTE);
        }
        return null;
    }
    
    public void logoutUser(){
        Session.get().replaceSession();
    }
}
