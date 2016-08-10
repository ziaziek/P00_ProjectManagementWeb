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
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

/**
 *
 * @author Przemo
 */
public class RegisterUser extends PMUnloggedPage {

    @SpringBean
    private UserManagementService userManagementService;
    
    private String email, pass1, pass2;
    
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
                user.setEmail(email);
                //TODO: user roles will haveto be managed somehow as well
                user.setRole(new Role(1, "Developer"));
                userManagementService.registerUser(user, pass1);
            }
            
        };
        TextField p1 = new PasswordTextField("pass1", new PropertyModel(this, "pass1"));
        TextField p2 =new PasswordTextField("pass2", new PropertyModel(this, "pass2"));
        f.add(new TextField("email", new PropertyModel(this, "email")).add(EmailAddressValidator.getInstance()));
        f.add(p1);
        f.add(p2);
        f.add(new EqualPasswordInputValidator(p1, p2));
        add(f);
    }
    
}
