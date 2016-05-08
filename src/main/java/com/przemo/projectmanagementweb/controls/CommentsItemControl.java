/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.domain.TaskComments;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public class CommentsItemControl extends Panel {
    
    public CommentsItemControl(String id, final IModel<TaskComments> model) {
        super(id, model);
        add(new Label("users.email"));
        add(new Label("comment"));
        add(new Label("date"));
        add(new Link("edit"){
                @Override
                public void onClick() {
                    setResponsePage(new CommentEditPage(model));
                }
                
            });
    }
    
}
