/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.BasePMPage;
import com.przemo.projectmanagementweb.domain.TaskComments;
import com.przemo.projectmanagementweb.services.CommentsService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public class CommentEditPage extends BasePMPage {
    
    public CommentEditPage(final IModel<TaskComments> model){
        super(model);
        Form form = new Form("form"){
            
            @Override
            protected void onSubmit() {
                new CommentsService().saveComment(model.getObject());
            }
            
        };
        form.add(new Label("users.email"));
        form.add(new TextArea("comment"));
        form.add(new Label("date"));
        add(form);
    }
}
