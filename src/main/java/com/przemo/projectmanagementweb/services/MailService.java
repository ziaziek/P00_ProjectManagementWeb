/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */

@Service
public class MailService {
    
    private MailSender mailSender;
    
    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }
    void sendAccountConfirmationEmail(String email, String link) {
        try{
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Project Management System account activation.");
        msg.setFrom("info@pncomp.com");
        msg.setTo(email);
        msg.setText("Please click the following below to activate your account. "+ link);
        try{
            mailSender.send(msg);
        } catch(MailException mex){
            LoggerFactory.getLogger(getClass()).error("From Mail Service MailException: ");
            LoggerFactory.getLogger(getClass()).error(mex.getMessage());
        }
        } catch(Exception ex){
            LoggerFactory.getLogger(getClass()).error("From Mail Service Exception: ");
        }
        
    }
    
}
