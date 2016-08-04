/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */

@Service
class MailService {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    void sendAccountConfirmationEmail(String email, String link) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Project Management System account activation.");
        msg.setFrom("info@pncomp.com");
        msg.setTo(email);
        msg.setText("Please click the link below to activate your account. "+ link);
        mailSender.send(msg);
    }
    
}
