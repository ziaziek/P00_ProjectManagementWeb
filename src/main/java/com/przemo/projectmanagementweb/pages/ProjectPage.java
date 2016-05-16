/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.services.ProjectService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemysław
 */
public class ProjectPage extends BasePMPage{
    
    @SpringBean
    ProjectService projectService;
    
    public ProjectPage(IModel<Projects> model){
        super(model);
        Form f = new Form("project_form"){

            @Override
            protected void onSubmit() {
                projectService.saveProject(model.getObject());
            }
            
        };
        f.add(new TextField("name"));
        f.add(new TextField("startdate"));
        f.add(new TextField("enddate"));
        f.add(new TextArea("description"));
        add(f);
    }
}
