/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.Task;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 *
 * @author Przemo
 */
public class TasksListPanel extends Panel {

    public TasksListPanel(String id, IModel<List<Task>> model){
        super(id, model);
        RepeatingView tasksView = new RepeatingView("tasksList", model);
        for(Task t: model.getObject()){
            tasksView.add(new TasksListItem(tasksView.newChildId(), new CompoundPropertyModel<>(t)));
        }
        
        add(tasksView);
        
        add(new Label("tasksCount", Model.of("Number of tasks: "+ model.getObject().size())));
    }
    
}
