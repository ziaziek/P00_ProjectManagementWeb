/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginStatusPanel;
import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class BasePMPage extends WebPage {
    
    @SpringBean
    private LoginService loginService;
    
    private FeedbackPanel fpanel;
    
    public BasePMPage() {
        super();
        initPage();
    }
    
    public BasePMPage(IModel model) {
        super(model);
        initPage();
    }
    
    private void initPage() {
        fpanel = new FeedbackPanel("feedback");
        add(fpanel);
        if (loginService.isLoggedIn()) {
            add(new LoginStatusPanel("loginStatusPanel", new CompoundPropertyModel<>(loginService.getLoggedInUser())));
            add(new Link("homeButton") {
                @Override
                public void onClick() {
                    setResponsePage(HomePage.class);
                }
                
            });            
        } else {
            setResponsePage(LoginPage.class);
        }
        
    }
}
