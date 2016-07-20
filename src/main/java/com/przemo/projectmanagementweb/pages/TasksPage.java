/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class TasksPage extends PMPage {

    @SpringBean
    private TaskService taskService;
    public TasksPage() {
        add(new Link("newtasklink"){
            @Override
            public void onClick() {
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(taskService.createNewTask())));
            }
            
        });
        add(new TasksListPanel("tasksList", new CompoundPropertyModel<>(taskService.getAllTasks())));
    }

    @Override
    protected Class getCurrentMenuClass() {
        return this.getClass();
    }
}
