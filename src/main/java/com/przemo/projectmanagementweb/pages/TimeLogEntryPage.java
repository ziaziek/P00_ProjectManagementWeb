/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.TimeLog;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public class TimeLogEntryPage extends BasePMPage {

    public TimeLogEntryPage() {
        initPage();
    }
    
    public TimeLogEntryPage(IModel<TimeLog> model){
        super(model);
        initPage();            
    }
    
    private void initPage(){
        add(new Label("task.id"));
    }
}
