/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.helpers.ApplicationHelper;
import com.przemo.projectmanagementweb.controls.CurrentProjectPanel;
import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.services.TaskService;
import java.util.List;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public abstract class ProjectFilterablePage extends PMPage {
    
    @SpringBean
    TaskService taskService;
    List<Task> tasks;
    
    public ProjectFilterablePage(){
        add(new CurrentProjectPanel("currentProject"));
    }
    
        @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        if(getSession().getAttribute(ApplicationHelper.SESSION_PROJECT)!=null){
            tasks = taskService.getBacklogTasksForProject((Projects) getSession().getAttribute(ApplicationHelper.SESSION_PROJECT));
        } else {
            tasks = taskService.getBacklogTasks();
        }
        addOrReplace(new TasksListPanel("tasksList", new CompoundPropertyModel<>(tasks)));     
    }
}
