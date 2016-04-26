/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import com.przemo.projectmanagementweb.controls.CommentsItemControl;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.domain.TaskType;
import com.przemo.projectmanagementweb.services.CommentsService;
import com.przemo.projectmanagementweb.services.SprintService;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public class TaskPage extends WebPage {
    
    public TaskPage(IModel<Task>model){
        super(model);
        
        Form form = new Form("form"){
            @Override
            protected void onSubmit() {
                new TaskService().saveTask(model.getObject());
                setResponsePage(HomePage.class);
            }
        };
        form.add(new TextField("title"));
        form.add(new TextField("description"));
        form.add(new TextField("estimatedTime"));
        form.add(new TextField("realTime"));
        form.add(new TextField("users.email"));
        form.add(new DropDownChoice("taskType", new TaskService().getTaskTypes(), new ChoiceRenderer<TaskType>(){
            @Override
            public Object getDisplayValue(TaskType object) {
                return object.getName();
            }

            @Override
            public String getIdValue(TaskType object, int index) {
                return String.valueOf(object.getId());
            }
            
        }));
        form.add(new DropDownChoice<>("sprint", new SprintService().retrieveAllSprints(), new ChoiceRenderer<Sprint>(){
            @Override
            public Object getDisplayValue(Sprint object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Sprint object, int index) {
                return String.valueOf(object.getId());
            }          
        }));
        form.add(new DropDownChoice("status", new TaskService().getAvailableStatuses(), new ChoiceRenderer<Status>(){
            @Override
            public Object getDisplayValue(Status object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Status object, int index) {
                return String.valueOf(object.getId());
            }          
        }));
        RepeatingView view = new RepeatingView("taskCommentses");
        new CommentsService().retrieveComments().stream().forEach((tc) -> {
            view.add(new CommentsItemControl(view.newChildId(), new CompoundPropertyModel<>(tc)));
        });
        add(view);
        add(form);
    }
}
