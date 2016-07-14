/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.pages.HomePage;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Przemo
 */
public class Application extends WebApplication {

    private static String DEPLOYMENT_MODE = "application.deployment.mode";

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.przemo.projectmanagementweb");
        ctx.refresh();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
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
