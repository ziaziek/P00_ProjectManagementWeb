/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.LoginStatusPanel;
import com.przemo.projectmanagementweb.domain.Users;
import com.przemo.projectmanagementweb.services.LoginService;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public abstract class BasePMPage extends WebPage {

    @SpringBean
    private LoginService loginService;

    private FeedbackPanel fpanel;

    protected Class[] topMenuClasses = new Class[]{HomePage.class, SprintsListPage.class, TasksPage.class, BacklogPage.class, ProjectsPage.class};
    protected String[] topMenuNames = new String[]{"Home", "Sprints", "Tasks", "Backlog", "Projects"};

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
        addTopMenu();
        if (loginService.isLoggedIn()) {
            add(new LoginStatusPanel("loginStatusPanel", new CompoundPropertyModel<>(loginService.getLoggedInUser())));
        } else {
            setResponsePage(LoginPage.class);
        }
    }

    private void addTopMenu() {
        add(new ListView<MenuItem>("topMenu", getMenuModel()) {

            @Override
            protected void populateItem(ListItem<MenuItem> li) {
                if (getCurrentMenuClass().equals(li.getModel().getObject().menuClass)) {
                    li.add(new AttributeModifier("class", Model.of("current_page_item")));
                }
                final Class cl = li.getModel().getObject().menuClass;
                li.add(new Link("link") {
                    @Override
                    public void onClick() {
                        setResponsePage(cl);
                    }
                }.add(new Label("linkText", Model.of(li.getModel().getObject().menuWicketId))));
            }
        }
        );
    }
    
    protected abstract Class getCurrentMenuClass();

    private IModel getMenuModel() {
        List<MenuItem> menuList = new ArrayList();
        for (int i = 0; i < topMenuClasses.length; i++) {
            MenuItem m = new MenuItem();
            m.menuClass = topMenuClasses[i];
            m.menuWicketId = topMenuNames[i];
            menuList.add(m);
        }
        MenuModel model = new MenuModel();
        model.setObject(menuList);
        return model;
    }

    class MenuItem {

        Class menuClass;
        String menuWicketId;
    }

    class MenuModel implements IModel {

        List<MenuItem> items;

        @Override
        public Object getObject() {
            return items;
        }

        @Override
        public void setObject(Object t) {
            if (t instanceof List) {
                items = (List<MenuItem>) t;
            }
        }

        @Override
        public void detach() {
            items = null;
        }

    }

}
