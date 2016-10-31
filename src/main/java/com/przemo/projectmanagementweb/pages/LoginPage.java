/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginPanel;
import org.apache.wicket.markup.html.link.Link;

/**
 *
 * @author Przemo
 */
public class LoginPage extends PMUnloggedPage {


    @Override
    protected Class getCurrentMenuClass() {
        return SprintsListPage.class;
    }

    @Override
    protected void initComponents() {
        add(new LoginPanel("innerPanel"));
            add(new Link("registerLink"){
                @Override
                public void onClick() {
                    setResponsePage(RegisterUser.class);
                }             
            });
    }

}
