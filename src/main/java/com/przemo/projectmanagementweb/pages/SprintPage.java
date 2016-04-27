/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.services.TaskService;
import java.util.List;
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
    
    private List<Status> statusList;
    
    public SprintPage(IModel<Sprint> model) {
        super(model);
        add(new Label("name"));
        add(new TextField("startDate"));
        add(new TextField("endDate"));
        add(new TextField("timeAvailable"));
        add(new Label("timeElapsed", Model.of("24h")));
        //instead of a single task panel, task panels for different sprint flows are rendered
        renderSprintFlowTaskLists(model);
    }
    
    private void renderSprintFlowTaskLists(IModel<Sprint> model){
        statusList = taskService.getAvailableStatuses();
        add(new TasksListPanel("tasksListToDo", new CompoundPropertyModel<>
                (taskService.getTasksForSprint(model.getObject(), statusList.stream().filter(o->o.getName().equals("ToDo")).findAny().get()))));
        add(new TasksListPanel("tasksListInProgress", new CompoundPropertyModel<>
                (taskService.getTasksForSprint(model.getObject(), statusList.stream().filter(o->o.getName().equals("In Progress")).findAny().get()))));
        add(new TasksListPanel("tasksListUnderReview", new CompoundPropertyModel<>
                (taskService.getTasksForSprint(model.getObject(), statusList.stream().filter(o->o.getName().equals("Under review")).findAny().get()))));
        add(new TasksListPanel("tasksListDone", new CompoundPropertyModel<>
                (taskService.getTasksForSprint(model.getObject(), statusList.stream().filter(o->o.getName().equals("Done")).findAny().get()))));
    }
    
}
