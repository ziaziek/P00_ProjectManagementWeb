/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.Users;
import com.przemo.projectmanagementweb.pages.LoginPage;
import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
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
        add(new Label("lastLogin", loginService.getLastLogin()));
        add(new Link("logoutLink"){
            @Override
            public void onClick() {
                loginService.logoutUser();
                setResponsePage(LoginPage.class);
            }
        });
    }
    
}
