/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.services.ProjectService;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemysław
 */
public class ProjectsPage extends PMPage {
    
    @SpringBean
    private ProjectService projectService;
    
    public ProjectsPage(){

        ListView<Projects> view = new ListView("plist",projectService.getAllProjects() ) {

            @Override
            protected void populateItem(ListItem li) {
                Link l = new Link("link"){

                    @Override
                    public void onClick() {
                        setResponsePage(new ProjectPage(new CompoundPropertyModel<>(li.getModel())));
                    }
                    
                };
                l.add(new Label("name", new PropertyModel(li.getModel(), "name")));
                li.add(l);
            }


        };
        add(view);
        add(new Link("new_project_link"){

            @Override
            public void onClick() {
                setResponsePage(new ProjectPage(new CompoundPropertyModel<>(new Projects())));
            }
            
        });
    }

    @Override
    protected Class getCurrentMenuClass() {
        return this.getClass();
    }
}
