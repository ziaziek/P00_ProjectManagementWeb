/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.ApplicationHelper;
import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Users;
import java.io.Serializable;
import java.util.Date;
import org.apache.wicket.Session;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class LoginService {

    public static final String USER_ATTRIBUTE = "user";

    public boolean login(final String username, final String password) {
        int uid = (int)HibernateUtil.runSQLQuery("select pr_user_login("+username+", "+password+")").get(0);
        if (uid>0) {
            Users u = (Users) HibernateUtil.runQuery("from Users where id="+uid).get(0);
            Session.get().setAttribute(USER_ATTRIBUTE, (Serializable) u);
            Session.get().setAttribute(ApplicationHelper.LAST_LOGIN, (Serializable) lastLogin(u));
            Session.get().bind();
            return true;
        } else {
            Session.get().replaceSession();
            return false;
        }
    }

    public boolean isLoggedIn() {
        return Session.get().getAttribute(LoginService.USER_ATTRIBUTE) != null;
    }

    public Users getLoggedInUser() {
        if (isLoggedIn()) {
            return (Users) Session.get().getAttribute(USER_ATTRIBUTE);
        }
        return null;
    }

    public void logoutUser() {
        Session.get().replaceSession();
    }

    public Date getLastLogin() {
        return (Date)Session.get().getAttribute(ApplicationHelper.LAST_LOGIN);
    }
    
    protected Date lastLogin(final Users user){
        return (Date) HibernateUtil.runQuery("select pr_last_login_for_user(" + user.getId() + ")").get(0);
    }
}
