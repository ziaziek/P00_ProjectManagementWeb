/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

/**
 *
 * @author Przemo
 */
public abstract class PMUnloggedPage extends BasePMPage{

        @Override
    protected void initPage() {       
        if (loginService.isLoggedIn()) {
            setResponsePage(SprintsListPage.class);
        } else {
            Session.get().replaceSession();
            fpanel = new FeedbackPanel("feedback");
            add(fpanel);
            add(new WebMarkupContainer("loginStatusPanel"));
            initComponents();
            
        }
    }
    protected abstract void initComponents();
    
}
