/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;

/**
 *
 * @author Przemo
 */
public class RegisterUser extends PMUnloggedPage {

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
                
            }
            
        };
        f.add(new TextField("username"));
        f.add(new TextField("pass1"));
        f.add(new TextField("pass2"));
        add(f);
    }
    
    
    
}
