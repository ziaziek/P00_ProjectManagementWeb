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
import com.przemo.projectmanagementweb.services.TimeLogService;
import com.przemo.projectmanagementweb.services.errors.SprintServiceSaveException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SprintPage extends PMPage {

    @SpringBean
    private TaskService taskService;
    
    @SpringBean
    private SprintService sprintService;
    
    @SpringBean
    private TimeLogService timeLogService;
    
    private List<Status> statusList;
    
    public SprintPage(IModel<Sprint> model) {
        super(model);
        Form f = new Form("form"){
            @Override
            protected void onSubmit() {
                try{
                    sprintService.saveSprint(model.getObject());
                success("Sprint saved successfully.");
                //setResponsePage(SprintsListPage.class);
                } catch(Exception ex){
                    error(ex.getMessage());
                }
            }
        };
        add(new Link("newtasklink"){
            @Override
            public void onClick() {
                Task t = taskService.createNewTask();
                t.setSprint(model.getObject());
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(t)));
            }          
        });
        
        f.add(new TextField("name"));
        f.add(new TextField<>("startDate"));
        f.add(new TextField("endDate"));
       
        f.add(new Label("timeAvailable", sprintService.getAvailableTime(model.getObject())));
        f.add(new Label("timeElapsed", Model.of(timeLogService.getTimeLoggedForSprint(model.getObject().getId()))));
        f.add(new Label("sprintStatus.name"));
        add(f);
        
        Form closeSprint = new Form("closeSprintForm"){
            @Override
            protected void onSubmit() {
                try {
                    sprintService.saveSprintWithStatus(model.getObject(), "Closed");
                } catch (SprintServiceSaveException ex) {
                    Logger.getLogger(SprintPage.class.getName()).log(Level.SEVERE, null, ex);
                    error(ex.getMessage());
                }
                setResponsePage(SprintsListPage.class);
            }     
        };
        add(closeSprint);
        closeSprint.setVisible(model.getObject().getSprintStatus()==null || !model.getObject().getSprintStatus().getName().equals("Closed"));
        
        Form currentSprint = new Form("currentSprintForm"){
            @Override
            protected void onSubmit() {
                try {
                    sprintService.saveSprintWithStatus(model.getObject(), "Current");
                } catch (SprintServiceSaveException ex) {
                    Logger.getLogger(SprintPage.class.getName()).log(Level.SEVERE, null, ex);
                    error(ex.getMessage());
                }
                setResponsePage(SprintsListPage.class);
            }
            
        };
        add(currentSprint);
        currentSprint.setVisible(model.getObject().getSprintStatus()==null || !(model.getObject().getSprintStatus().getName().equals("Current")
                || model.getObject().getSprintStatus().getName().equals("Closed")));
        //instead of a single task panel, task panels for different sprint flows are rendered
        renderSprintFlowTaskLists(model);
    }
    
    
    private void renderSprintFlowTaskLists(IModel<Sprint> model){
        statusList = taskService.getAvailableStatuses();
        
        add(new TasksListPanel("tasksListToDo", new CompoundPropertyModel<>
                (filterTasksByStatus(model, "ToDo"))));
        add(new TasksListPanel("tasksListInProgress", new CompoundPropertyModel<>
                (filterTasksByStatus(model, "In Progress"))));
        add(new TasksListPanel("tasksListUnderReview", new CompoundPropertyModel<>
                (filterTasksByStatus(model, "Under Review"))));
        add(new TasksListPanel("tasksListDone", new CompoundPropertyModel<>
                (filterTasksByStatus(model, "Done"))));
    }
    
    private List<Task> filterTasksByStatus(final IModel<Sprint> model, final String status){
        Optional res = statusList.stream().filter(o->o.getName().equals(status)).findAny();
        if (res.isPresent()){
            return taskService.getTasksForSprint(model.getObject(), (Status) res.get());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected Class getCurrentMenuClass() {
        return SprintsListPage.class;
    }
    
}
