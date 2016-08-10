/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Users;
import java.util.Random;
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

    private final Random random = new Random();

    public int registerUser(final Users user, final String password) {
        //TODO: password needs to be encrypted!!!!
        //save information and send confirmation email
        final String activationCode = generateActivationCode();
        try {
            final String query = "select pr_create_user('" + user.getEmail() + "'," + user.getRole().getId() + ",'" + password + "','" + activationCode + "', 60)";
            LoggerFactory.getLogger(getClass()).info(query);
            HibernateUtil.runSQLQuery(query);
            LoggerFactory.getLogger(getClass()).info("Trying to send email.");
            LoggerFactory.getLogger(getClass()).info("Is mail service null? "+ (mailService==null));
            mailService.sendAccountConfirmationEmail(user.getEmail(), activationCode);
            LoggerFactory.getLogger(getClass()).info("Email sent.");
            return 0;
        } catch (Exception ex) {
            LoggerFactory.getLogger(getClass()).error(ex.getMessage());
            return -1;
        }

    }
    
    protected final String generateActivationCode() {

        char[] arr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'v',
            'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            s.append(arr[random.nextInt(arr.length)]);
        }
        return s.toString();
    }
}
