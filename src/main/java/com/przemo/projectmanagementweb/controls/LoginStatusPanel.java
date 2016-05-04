/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.Users;
import com.przemo.projectmanagementweb.pages.HomePage;
import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class LoginStatusPanel extends Panel {
    
    @SpringBean
    LoginService loginService;
    
    public LoginStatusPanel(String id, IModel<Users> model) {
        super(id, model);
        add(new Label("email"));
        add(new Label("lastLogin", Model.of("2016-04-12 12:54:00")));
        add(new Link("logoutLink"){
            @Override
            public void onClick() {
                loginService.logoutUser();
                setResponsePage(HomePage.class);
            }
            
        });
    }
    
}
