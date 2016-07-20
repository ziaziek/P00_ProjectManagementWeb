/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.TaskComments;
import com.przemo.projectmanagementweb.services.CommentsService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class CommentEditPage extends PMPage {
    
    @SpringBean
    private CommentsService commentsService;
    
    public CommentEditPage(final IModel<TaskComments> model){
        super(model);
        Form form = new Form("form"){
            
            @Override
            protected void onSubmit() {
                commentsService.saveComment(model.getObject());
                success("Comments saved successfully.");
            }
            
        };
        form.add(new Label("users.email"));
        form.add(new TextArea("comment"));
        form.add(new Label("date"));
        add(form);
    }

    @Override
    protected Class getCurrentMenuClass() {
        return TasksPage.class;
    }
}
