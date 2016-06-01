/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.TimeLog;
import com.przemo.projectmanagementweb.pages.TimeLogEntryPage;
import com.przemo.projectmanagementweb.services.TimeLogService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class TimeLogViewControl extends Panel{
    
    @SpringBean
    TimeLogService timeLogService;
    
    public TimeLogViewControl(String id, IModel<TimeLog> model) {
        super(id, model);
        add(new Label("time"));
        add(new Label("date"));
        add(new Link("editlink"){
            @Override
            public void onClick() {
                setResponsePage(new TimeLogEntryPage(model));
            }
        });
        add(new Link("deletelink"){
            @Override
            public void onClick() {
                timeLogService.delete(model.getObject());
            }
        });
    }
    
}
