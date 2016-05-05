/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.pages.TaskPage;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class TasksListItem extends Panel {
    
    @SpringBean
    TaskService taskService;
    
    public TasksListItem(String id, IModel<Task> model) {
        super(id, model);
       
        Link l = new Link("link"){
            @Override
            public void onClick() {
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(taskService.getTaskById(model.getObject().getId()))));
            }    
        };
        
        l.add(new Label("title"));
        l.add(new Label("estimatedTime"));
        l.add(new Label("owner", new PropertyModel(model, "users.email")));
        l.add(new Label("sprint.name", Model.of(model.getObject().getSprint()==null ? "N/A" : model.getObject().getSprint().getName())));
        add(l);
    }
    
}
