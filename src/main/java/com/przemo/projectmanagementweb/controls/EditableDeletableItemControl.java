/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 * @param <T>
 */
public abstract class EditableDeletableItemControl<T> extends Panel {
    
    private static final String EDIT_LINK_PLACEHOLDER="edit_link";
    private static final String DELETE_LINK_PLACEHOLDER="delete_link";
    
    
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public EditableDeletableItemControl(String id, IModel<T> model) {
        this(id, model, true, true);
    }
    
    public EditableDeletableItemControl(String id, IModel<T>model, final boolean editVisible, final boolean deleteVisible){
     super(id, model);
     initControl(editVisible, deleteVisible);
    }
    
    
    
    private void initControl(final boolean edit, final boolean delete){
        add(new Link(EDIT_LINK_PLACEHOLDER){
            @Override
            public void onClick() {
                editLinkAction();
            }
            
        }.setVisible(edit));
        add(new Link(DELETE_LINK_PLACEHOLDER){
            @Override
            public void onClick() {
                deleteLinkAction();
            }
            
        }.setVisible(delete));
    }

    protected abstract void editLinkAction();

    protected abstract void deleteLinkAction();
    
    protected T getDefaultModelObjectForControl(){
        return (T) super.getDefaultModelObject();
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        if(className!=null){
           tag.append("class", className, " "); 
        }       
    }
    
}
