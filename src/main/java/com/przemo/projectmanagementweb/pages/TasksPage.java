/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Przemo
 */
public class TasksPage extends ProjectFilterablePage {

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
