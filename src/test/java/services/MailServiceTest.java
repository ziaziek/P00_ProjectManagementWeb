/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.przemo.projectmanagementweb.services.MailService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author Przemo
 */
public class MailServiceTest {
    
    static MailService mailService;
    static JavaMailSenderImpl mailSender;
    static ApplicationContext ctx;
    public MailServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
         
        mailSender = (JavaMailSenderImpl) ctx.getBean("mailSender");
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Ignore
    @Test
    public void initTest(){
        Assert.assertNotNull("Mail service not initialised.", mailService);
        Assert.assertNotNull(mailSender);
        Assert.assertEquals("ziaziek@poczta.fm", mailSender.getUsername());
    }
    
    @Ignore
    @Test
    public void sendEmailTest(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("info@pncomp.com");
        msg.setFrom("ziaziek@poczta.fm");
        msg.setSubject("AAA");
        msg.setText("TEST");
        Assert.assertEquals("info@pncomp.com", msg.getTo()[0]);
        Assert.assertEquals("AAA", msg.getSubject());
        try{
            mailSender.send(msg);
        } catch(MailException ex){
            fail(ex.getMessage());
        }
        
    }
}
