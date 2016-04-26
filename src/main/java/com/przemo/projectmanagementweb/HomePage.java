/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.controls.SprintsListPanel;
import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.services.SprintService;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Przemo
 */
public class HomePage extends WebPage {

    public HomePage(){
        add(new TasksListPanel("alltasks",new CompoundPropertyModel(new TaskService().getAllTasks())));
        add(new SprintsListPanel("allsprints", new CompoundPropertyModel<>(new SprintService().retrieveAllSprints())));
        add(new Link("backlogLink") {
            @Override
            public void onClick() {
                setResponsePage(BacklogPage.class);
            }
        });
        add(new Link("newTaskLink"){
            @Override
            public void onClick() {
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(new Task())));
            }
        });
    }
}
