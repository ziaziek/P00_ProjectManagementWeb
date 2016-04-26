/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.TaskPage;
import com.przemo.projectmanagementweb.domain.Task;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Przemo
 */
public class TasksListItem extends Panel {
    
    public TasksListItem(String id, IModel<Task> model) {
        super(id, model);
       
        Link l = new Link("link"){
            @Override
            public void onClick() {
                setResponsePage(new TaskPage(model));
            }    
        };
        
        l.add(new Label("title"));
        l.add(new Label("estimatedTime"));
        l.add(new Label("owner", new PropertyModel(model, "users.email")));
        l.add(new Label("sprint.name", Model.of(model.getObject().getSprint()==null ? "N/A" : model.getObject().getSprint().getName())));
        add(l);
    }
    
}
