/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author Przemo
 */
public class LoginPage extends WebPage {
    
    public LoginPage(){
        Session.get().replaceSession();
        add(new LoginPanel("loginPanel"));
    }
}
