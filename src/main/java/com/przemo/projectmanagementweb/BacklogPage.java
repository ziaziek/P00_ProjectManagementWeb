/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Przemo
 */
public class BacklogPage extends BasePMPage {
    
    public BacklogPage(){
        add(new TasksListPanel("tasksList", new CompoundPropertyModel<>(new TaskService().getBacklogTasks())));
    }
}
