/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Users;
import com.przemo.projectmanagementweb.helpers.CodeGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class UserManagementService {

    @Autowired
    MailService mailService;

    private String registrationIP;

    public void setRegistrationIP(String registrationIP) {
        this.registrationIP = registrationIP;
    }

    public int registerUser(final Users user, final String password, final String linkToRespond) {
        //TODO: password needs to be encrypted!!!!
        //save information and send confirmation email
        final String activationCode = CodeGenerator.generateCode(user.getEmail());
        try {
            final String query = "select pr_create_user('" + user.getEmail() + "'," + user.getRole().getId() + ",'" + password + "','" + activationCode + "', 60)";
            HibernateUtil.runSQLQuery(query);
            mailService.sendAccountConfirmationEmail(user.getEmail(), linkToRespond + "?activationCode=" + activationCode);
            return 0;
        } catch (Exception ex) {
            LoggerFactory.getLogger(getClass()).error(ex.getMessage());
            return -1;
        }

    }

    public Users getUserForCode(final String code) {
        int uid = -1;
        if (!CodeGenerator.sanitiseCode(code).equals("")) {
            uid = (int) HibernateUtil.runSQLQuery("select coalesce((select user_id from users_activation where activation_code='" + code + "'),-1)").get(0);
        }
        return uid>0 ? (Users) HibernateUtil.runQuery("from Users where id=" + uid).get(0): null;
    }

    public boolean activateUser(String code) {
        Users u = getUserForCode(code);
        return u != null && u.getId() > 0 && u.getEmail() != null && CodeGenerator.verifyCode(code, u.getEmail()) && (boolean) HibernateUtil.runSQLQuery("select pr_activate_user('" + code + "')").get(0);
    }
}
