/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.services.LoginService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class HomePage extends WebPage {

    @SpringBean
    LoginService loginService;
    
    public HomePage(){
        if(!loginService.isLoggedIn()){
            setResponsePage(LoginPage.class);
        } else {
            addMainPanel();
        }
    }
    
    private void addMainPanel(){
            add(new Link("sprintLink"){
            @Override
            public void onClick() {
                setResponsePage(SprintsListPage.class);
            }
            
        });
        add(new Link("backlogLink"){
            @Override
            public void onClick() {
                setResponsePage(BacklogPage.class);
            }
            
        });
        add(new Link("taskLink"){
            @Override
            public void onClick() {
                setResponsePage(TasksPage.class);
            }
            
        });
        add(new Link("adminLink"){
            @Override
            public void onClick() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        }
}
