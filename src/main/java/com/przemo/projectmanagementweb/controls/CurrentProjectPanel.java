/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.helpers.ApplicationHelper;
import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.services.ProjectService;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Przemo
 */
public class CurrentProjectPanel extends Panel{
    
    @SpringBean
    private ProjectService projectService;
    private Projects project;
    
    public CurrentProjectPanel(String id) {
        super(id);
        LoggerFactory.getLogger(getClass()).info("Constructing dropdown.");
        if(getSession().getAttribute(ApplicationHelper.SESSION_PROJECT)!=null){
            project = (Projects) getSession().getAttribute(ApplicationHelper.SESSION_PROJECT); 
        }
        Form f = new Form("form"){
            @Override
            protected void onSubmit() {
                if(project!=null && project.getId()!=0){
                   getSession().setAttribute(ApplicationHelper.SESSION_PROJECT, project); 
                } else {
                    getSession().removeAttribute(ApplicationHelper.SESSION_PROJECT);
                }
                
            }
            
        };
        f.add(new DropDownChoice<Projects>("project", new PropertyModel(this, "project"), listOfProjectsToChoose() , new ChoiceRenderer<Projects>(){
            @Override            
            public Object getDisplayValue(Projects object) {
                return object.getName();
            }

            @Override
            public String getIdValue(Projects object, int index) {
                return object.getName();
            }           
        }){
            
            @Override
            protected void onSelectionChanged(Projects newSelection) {
                
                project = newSelection;
                LoggerFactory.getLogger(getClass()).info(project.getName());
            }
        });
        add(f);
    }
    
    private List<Projects> listOfProjectsToChoose(){
        List<Projects> r = new ArrayList<>();
        Projects p = new Projects(0, "No project", null);
        r.add(p);
        r.addAll(projectService.getAllProjects());
        return r;
    }
    
}
