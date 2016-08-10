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

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    void sendAccountConfirmationEmail(String email, String link) {
        SimpleMailMessage msg = new SimpleMailMessage();
        LoggerFactory.getLogger(getClass()).info("Message sender host: "+ ((JavaMailSenderImpl)mailSender).getHost());
        try{
          msg.setSubject("Project Management System account activation.");
        msg.setFrom("info@pncomp.com");
        msg.setTo(email);
        msg.setText("Please click the link below to activate your account. "+ link);
        LoggerFactory.getLogger(getClass()).debug("Message prepared to be sent.");
        try{
            mailSender.send(msg);
            LoggerFactory.getLogger(getClass()).debug("Message sent.");
        } catch(MailException mex){
            LoggerFactory.getLogger(getClass()).error(mex.getMessage());
        }
        } catch(Exception ex){
            LoggerFactory.getLogger(getClass()).error(ex.getMessage());
        }
        
    }
    
}
