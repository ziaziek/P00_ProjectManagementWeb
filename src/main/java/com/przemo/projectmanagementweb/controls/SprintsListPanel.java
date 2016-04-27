/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.controls;

import com.przemo.projectmanagementweb.pages.SprintPage;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.services.SprintService;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Przemo
 */
public class SprintsListPanel extends Panel {
    
    public SprintsListPanel(String id, IModel<List<Sprint>> model) {
        super(id, model);
        add(new ListView<Sprint>("sprintlistview", new SprintService().retrieveAllSprints()) {
            @Override
            protected void populateItem(ListItem<Sprint> li) {
                Link l = new Link("link", li.getModel()) {
                    @Override
                    public void onClick() {
                        setResponsePage(new SprintPage(li.getModel()));
                    }
                };
                l.add(new Label("name", new PropertyModel(li.getModel(), "name")));
                li.add(l);
            }
        });
    }
    
}
