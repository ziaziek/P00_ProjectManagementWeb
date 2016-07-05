/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginStatusPanel;
import com.przemo.projectmanagementweb.domain.Users;
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

    private final Class[] linkPages = new Class[]{HomePage.class, SprintsListPage.class, TasksPage.class, BacklogPage.class, ProjectsPage.class};
    private final String[] linkNames = new String[]{"homeLink", "sprintsLink", "tasksLink", "backlogLink", "projectsLink"};
    
    public BasePMPage() {
        super();
        initPage();
    }

    public BasePMPage(IModel model) {
        super(model);
        initPage();
    }

    protected Users getCurrentUser() {
        return loginService.getLoggedInUser();
    }

    private void initPage() {
        fpanel = new FeedbackPanel("feedback");
        add(fpanel);
        if (loginService.isLoggedIn()) {
            addTopMenuLinks();
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

    private void addTopMenuLinks() {
        for(int i=0; i< linkPages.length; i++){
            final Class p = linkPages[i];
            add(new Link(linkNames[i]) {          
                @Override
                public void onClick() {
                    setResponsePage(p);
                }
            });
        }
    }
}
