/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.services.ProjectService;
import java.util.List;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemysław
 */
public class ProjectsPage extends BasePMPage {
    
    @SpringBean
    private ProjectService projectService;
    
    public ProjectsPage(){
        
        List<Projects> ps = projectService.getAllProjects();
        RepeatingView view = new RepeatingView("plist");
        ps.stream().forEach(p->view.add(new Link(view.newChildId(), Model.of(p.getName())) {

            @Override
            public void onClick() {
                setResponsePage(new ProjectPage(new CompoundPropertyModel<>(p)));
            }
        }));
        add(view);
        add(new Link("new_project_link"){

            @Override
            public void onClick() {
                setResponsePage(new ProjectPage(new CompoundPropertyModel<>(new Projects())));
            }
            
        });
    }
}
