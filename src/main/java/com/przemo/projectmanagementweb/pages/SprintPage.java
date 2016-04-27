/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class SprintPage extends BasePMPage {

    @SpringBean
    private TaskService taskService;
     
    public SprintPage(IModel<Sprint> model) {
        super(model);
        add(new Label("name"));
        add(new TextField("startDate"));
        add(new TextField("endDate"));
        add(new TextField("timeAvailable"));
        add(new Label("timeElapsed", Model.of("24h")));
        add(new TasksListPanel("tasksList", new CompoundPropertyModel<>
                (taskService.getTasksForSprint(model.getObject()))));
    }
    
}
