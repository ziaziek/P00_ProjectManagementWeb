/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.TimeLog;
import com.przemo.projectmanagementweb.services.TaskService;
import com.przemo.projectmanagementweb.services.TimeLogService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemysław
 */
public class TimeLogEntry extends Panel {

    @SpringBean
    TimeLogService timeLogService;
    
    public TimeLogEntry(String id, IModel<TimeLog> model) {
        super(id, model);
        Form timeForm = new Form("timelogform"){

            @Override
            protected void onSubmit() {
                timeLogService.saveTimeLog(model.getObject());
            }
            
        };
        timeForm.add(new TextField("time"));
        timeForm.add(new TextField("date"));
        add(timeForm);
    }
    
}
