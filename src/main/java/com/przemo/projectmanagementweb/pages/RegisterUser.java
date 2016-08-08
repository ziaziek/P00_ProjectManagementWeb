/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.Role;
import com.przemo.projectmanagementweb.domain.Users;
import com.przemo.projectmanagementweb.services.UserManagementService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class RegisterUser extends PMUnloggedPage {

    @SpringBean
    private UserManagementService userManagementService;
    
    private String username, pass1, pass2;
    
    @Override
    protected Class getCurrentMenuClass() {
        return HomePage.class;
    }


    @Override
    protected void initComponents() {
        Form f = new Form("form"){
            @Override
            protected void onSubmit() {
                Users user = new Users();
                user.setEmail(username);
                user.setRole(new Role(0, "Developer"));
                userManagementService.registerUser(user, pass1);
            }
            
        };
        TextField p1 = new TextField("pass1");
        TextField p2 =new TextField("pass2");
        f.add(new TextField("username"));
        f.add(p1);
        f.add(p2);
        f.add(new EqualPasswordInputValidator(p1, p2));
        add(f);
    }
    
    
    
}
