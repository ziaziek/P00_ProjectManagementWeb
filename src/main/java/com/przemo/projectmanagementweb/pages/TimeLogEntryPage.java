/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.TimeLog;
import com.przemo.projectmanagementweb.services.TaskService;
import com.przemo.projectmanagementweb.services.TimeLogService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Przemo
 */
public class TimeLogEntryPage extends PMPage {

    @SpringBean
    TimeLogService timeLogService;

    @SpringBean
    TaskService taskService;

    public TimeLogEntryPage(IModel<TimeLog> model, int taskId) {
        super(model);
        LoggerFactory.getLogger(getClass()).info("SBuilding time log entry page");
        add(new Label("task.name", new PropertyModel(new Model(taskService.getTaskById(taskId)), "title")));
        Form f = new Form("form") {
            @Override
            protected void onSubmit() {
                model.getObject().setTask(taskId);
                timeLogService.saveTimeLog(model.getObject());
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(taskService.getTaskById(taskId))));
            }
        };
        f.add(new TextField("date", new PropertyModel(model, "date")));
        f.add(new TextField("time"));
        add(f);
    }

    @Override
    protected Class getCurrentMenuClass() {
        return TasksPage.class;
    }

}
