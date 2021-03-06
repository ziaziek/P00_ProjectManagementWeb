/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.TaskComments;
import com.przemo.projectmanagementweb.pages.CommentEditPage;
import com.przemo.projectmanagementweb.pages.TaskPage;
import com.przemo.projectmanagementweb.services.CommentsService;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class CommentsItemControl extends EditableDeletableItemControl<TaskComments> {
    
    @SpringBean
    CommentsService commentsService;
    @SpringBean
    TaskService taskService;
    
    public CommentsItemControl(String id, final IModel<TaskComments> model) {
        super(id, model);
        add(new Label("users.email"));
        add(new Label("comment"));
        add(new Label("date"));
    }


    @Override
    protected void editLinkAction() {
        setResponsePage(new CommentEditPage(new CompoundPropertyModel(getDefaultModel())));
    }

    @Override
    protected void deleteLinkAction() {
        commentsService.delete((TaskComments) getDefaultModelObject());
        setResponsePage(new TaskPage(new CompoundPropertyModel(taskService.getTaskById(getDefaultModelObjectForControl().getTask()))));
    }
    
}
