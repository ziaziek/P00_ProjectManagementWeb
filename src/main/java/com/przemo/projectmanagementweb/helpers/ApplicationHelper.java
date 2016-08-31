/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.helpers;

import com.przemo.projectmanagementweb.services.LoginService;
import java.util.Date;
import org.apache.wicket.Session;

/**
 *
 * @author Przemo
 */
public class ApplicationHelper {
    
    public static final String SESSION_PROJECT = "project";
    public static final String LAST_LOGIN = "lastlogin";
    
    public static boolean isUserLoggedIn(Session session){
        return session.getAttribute(LoginService.USER_ATTRIBUTE)!=null;
    }
    
    public static Date lastLogin(Session session){
        return (Date) session.getAttribute(LAST_LOGIN);
    }

}
