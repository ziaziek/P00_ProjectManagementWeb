/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.TasksListPanel;
import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.services.ProjectService;
import com.przemo.projectmanagementweb.services.TaskService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemysław
 */
public class ProjectPage extends PMPage{
    
    @SpringBean
    ProjectService projectService;
    @SpringBean
    TaskService taskService;
    
    public ProjectPage(IModel<Projects> model){
        super(model);

        Form f;
        f = new Form("form"){
            
            @Override
            protected void onSubmit() {
                projectService.saveProject(model.getObject());
                setResponsePage(SprintsListPage.class);
            }
            
        };
        f.add(new TextField("name"));
        f.add(new TextField("startdate"));
        f.add(new TextField("enddate"));
        f.add(new TextArea("description"));
        add(f);
        add(new Link("newtasklink"){
            @Override
            public void onClick() {
                Task t = new Task();
                t.setProjects(model.getObject());
                setResponsePage(new TaskPage(new CompoundPropertyModel<>(t)));
            }
            
        });
        add(new TasksListPanel("tasksList", new CompoundPropertyModel<>(taskService.getTasksForProject(model.getObject()))));
    }

    @Override
    protected Class getCurrentMenuClass() {
        return ProjectsPage.class;
    }
}
