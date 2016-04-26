/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.services.ServiceUtil;
import org.apache.wicket.model.CompoundPropertyModel;

/**
 *
 * @author Przemo
 */
public class TasksPage extends BasePMPage {

    public TasksPage() {
        add(new TasksListPanel("tasksList", new CompoundPropertyModel<>(ServiceUtil.getTaskService().getAllTasks())));
    }
}
