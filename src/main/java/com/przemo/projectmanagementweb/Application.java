/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.pages.SprintsListPage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Przemo
 */
public class Application extends WebApplication {

    private static final String DEPLOYMENT_MODE = "application.deployment.mode";
    private static final String APPLICATION_VERSION_PROPERTY = "application.version" ;
    
    public static String APPLICATION_VERSION;
    
    @Override
    public Class<? extends Page> getHomePage() {
        return SprintsListPage.class;
    }
    
    @Override
    protected void init() {
        super.init();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.refresh();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
        Properties applicationProperties = new Properties();
        try{
            applicationProperties.load(this.getClass().getResourceAsStream("application.properties"));
            APPLICATION_VERSION=applicationProperties.getProperty(APPLICATION_VERSION_PROPERTY);
        } catch (IOException ex) {
            APPLICATION_VERSION="_";
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        Properties applicationProperties = new Properties();
        try {
            applicationProperties.load(this.getClass().getResourceAsStream("application.properties"));
            String dm = applicationProperties.get(DEPLOYMENT_MODE).toString();
            return dm.equals("deployment") ? RuntimeConfigurationType.DEPLOYMENT : RuntimeConfigurationType.DEVELOPMENT;
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.getConfigurationType();
    }

}
