/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.pages.HomePage;
import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class LoginPanel extends Panel {
    
    @SpringBean
    private LoginService loginService;
    
    private String username, password;
    public LoginPanel(String id) {
        super(id);
        Form form = new Form("loginPanel"){
            @Override
            protected void onSubmit() {
                WebClientInfo info = (WebClientInfo) Session.get().getClientInfo();   
                if(loginService.login(username, password, info.getProperties().getRemoteAddress(), info.getUserAgent())){
                    setResponsePage(HomePage.class);
                }
            }   
        };
        form.add(new TextField("username", new PropertyModel(this, "username")));
        form.add(new PasswordTextField("password", new PropertyModel<>(this, "password")));
        add(form);
    }
    
}
