/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.CommentsItemControl;
import com.przemo.projectmanagementweb.controls.TimeLogViewControl;
import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.domain.TaskComments;
import com.przemo.projectmanagementweb.domain.TaskType;
import com.przemo.projectmanagementweb.domain.TimeLog;
import com.przemo.projectmanagementweb.services.CommentsService;
import com.przemo.projectmanagementweb.services.ProjectService;
import com.przemo.projectmanagementweb.services.SprintService;
import com.przemo.projectmanagementweb.services.TaskService;
import com.przemo.projectmanagementweb.services.TimeLogService;
import java.util.Date;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.visit.IVisit;

/**
 *
 * @author Przemo
 */
public class TaskPage extends BasePMPage {
    
    @SpringBean
    private TaskService taskService;
    
    @SpringBean
    private CommentsService commentsService;
    
    @SpringBean
    private SprintService sprintService;
    
    @SpringBean
    private ProjectService projectsService;
    
    @SpringBean
    private TimeLogService timeLogService;
    
            
    public TaskPage(IModel<Task> model){
        super(model);

        Form form = new Form("form"){
            @Override
            protected void onSubmit() {
                taskService.saveTask(model.getObject());
                setResponsePage(HomePage.class);
            }
        };
        form.add(new TextField("title"));
        form.add(new TextArea("description"));
        form.add(new TextField("estimatedTime"));
        form.add(new Label("summaryTime", Model.of(timeLogService.getTimeLoggedForTask(model.getObject().getId()).toHours()+ " hours")));
        form.add(new TextField("users.email"));
        form.add(new DropDownChoice("taskType", taskService.getTaskTypes(), new ChoiceRenderer<TaskType>(){
            @Override
            public Object getDisplayValue(TaskType object) {
                return object.getName();
            }

            @Override
            public String getIdValue(TaskType object, int index) {
                return String.valueOf(object.getId());
            }
            
        }).setEnabled(!ticketIsClosed(model.getObject())));
        form.add(new DropDownChoice<>("sprint", sprintService.retrieveAllSprints(), new ChoiceRenderer<Sprint>(){
            @Override
            public Object getDisplayValue(Sprint object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Sprint object, int index) {
                return String.valueOf(object.getId());
            }          
        }).setEnabled(!ticketIsClosed(model.getObject())));
        form.add(new DropDownChoice("status", taskService.getAvailableStatuses(), new ChoiceRenderer<Status>(){
            @Override
            public Object getDisplayValue(Status object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Status object, int index) {
                return String.valueOf(object.getId());
            }          
        }));
        form.add(new DropDownChoice("projects", projectsService.getAllProjects(), new ChoiceRenderer<Projects>(){
            @Override
            public Object getDisplayValue(Projects object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Projects object, int index) {
                return String.valueOf(object.getId());
            } 
        }).setEnabled(!ticketIsClosed(model.getObject())));
        
        RepeatingView view = new RepeatingView("taskCommentses");
        commentsService.getCommentsForTask(model.getObject().getId()).stream().forEach((tc) -> {
            view.add(new CommentsItemControl(view.newChildId(), new CompoundPropertyModel<>(tc)));
        });
        add(view);
        
        RepeatingView timeView = new RepeatingView("timelog_entry");
        timeLogService.getTimeLogs(model.getObject().getId()).stream().forEach((tl-> timeView.add(new TimeLogViewControl(timeView.newChildId(), 
                new CompoundPropertyModel<>(tl)))));
        add(timeView);
        
        add(new Link("newentrylink"){
            @Override
            public void onClick() {
                setResponsePage(new TimeLogEntryPage(new CompoundPropertyModel<>(new TimeLog()), model.getObject().getId()));
            }
            
        });
        
        add(new Link("newcommentslink"){
            @Override
            public void onClick() {
                TaskComments tc = new TaskComments();
                tc.setTask(model.getObject().getId());
                tc.setUsers(getCurrentUser());
                tc.setDate(new Date());
                setResponsePage(new CommentEditPage(new CompoundPropertyModel<>(tc)));
            }         
        });
        
        add(form);
        //Form should be disabled if closed
        if(ticketIsClosed(model.getObject())){
           disableTaskForm(form, model); 
        }      
    }
    
    private boolean ticketIsClosed(Task t){
        return t.getStatus()!=null && t.getStatus().getName().equals("Closed");
    }
    
    private void disableTaskForm(Form form, IModel<Task> model){
        form.visitFormComponents((Object t, IVisit ivisit) -> {
            if(t instanceof TextField || t instanceof TextArea){
                ((Component)t).setEnabled(false);
            }
        });
    }
    
}
