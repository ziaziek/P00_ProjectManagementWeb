/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.services.SprintService;
import com.przemo.projectmanagementweb.services.TaskService;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
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
    
    @SpringBean
    private SprintService sprintService;
    
    private List<Status> statusList;
    
    public SprintPage(IModel<Sprint> model) {
        super(model);
        Form f = new Form("form"){
            @Override
            protected void onSubmit() {
                sprintService.saveSprint(model.getObject());
                success("Sprint saved successfully.");
                //setResponsePage(SprintsListPage.class);
            }
        };
        add(new Link("newtasklink"){
            @Override
            public void onClick() {
                Task t = new Task();
                t.setSprint(model.getObject());
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(t)));
            }          
        });
        
        f.add(new TextField("name"));
        f.add(new TextField<>("startDate"));
        f.add(new TextField("endDate"));
        f.add(new TextField("timeAvailable"));
        f.add(new Label("timeElapsed", Model.of("24h")));
        add(f);
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
