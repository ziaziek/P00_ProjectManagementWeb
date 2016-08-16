/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.services.UserManagementService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class UserActivationPage extends PMUnloggedPage {

    @SpringBean
    UserManagementService userManagementService;
    
    public UserActivationPage(final String code){
        super();
         add(new Link("loginLink"){
                @Override
                public void onClick() {
                    setResponsePage(LoginPage.class);
                }          
            });
        if(userManagementService.activateUser(code)){
            add(new Label("activation", Model.of("Activation successful. Go to Login Page.")));
        } else {
            add(new Label("activation", Model.of("Activation could not be finalised. Your code may have expired.")));
        }
        
        
    }
    @Override
    protected void initComponents() {
        
    }

    @Override
    protected Class getCurrentMenuClass() {
        return HomePage.class;
    }
    
}
