/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.TimeLog;
import com.przemo.projectmanagementweb.pages.TaskPage;
import com.przemo.projectmanagementweb.pages.TimeLogEntryPage;
import com.przemo.projectmanagementweb.services.TaskService;
import com.przemo.projectmanagementweb.services.TimeLogService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class TimeLogViewControl extends EditableDeletableItemControl<TimeLog>{
    
    @SpringBean
    TimeLogService timeLogService;
    
    @SpringBean
    TaskService taskService;
    
    public TimeLogViewControl(String id, IModel<TimeLog> model) {
        super(id, model);
        add(new Label("time"));
        add(new Label("date"));
    }

    @Override
    protected void editLinkAction() {
        setResponsePage(new TimeLogEntryPage((IModel<TimeLog>) getDefaultModel(), getDefaultModelObjectForControl().getTask()));
    }

    @Override
    protected void deleteLinkAction() {
        timeLogService.delete(getDefaultModelObjectForControl());
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(taskService.getTaskById(getDefaultModelObjectForControl().getTask()))));
    }
    
}
