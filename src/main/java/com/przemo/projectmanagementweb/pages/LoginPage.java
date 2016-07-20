/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginPanel;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 *
 * @author Przemo
 */
public class LoginPage extends BasePMPage {

    @Override
    protected void initPage() {       
        if (loginService.isLoggedIn()) {
            setResponsePage(HomePage.class);
        } else {
            Session.get().replaceSession();
            fpanel = new FeedbackPanel("feedback");
            add(fpanel);
            add(new WebMarkupContainer("loginStatusPanel"));
            add(new LoginPanel("loginPanel"));
        }
    }
    @Override
    protected Class getCurrentMenuClass() {
        return HomePage.class;
    }
}
