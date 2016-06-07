/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.Session;

/**
 *
 * @author Przemo
 */
public class ApplicationHelper {
    
    public static boolean isUserLoggedIn(Session session){
        return session.getAttribute(LoginService.USER_ATTRIBUTE)!=null;
    }

}
