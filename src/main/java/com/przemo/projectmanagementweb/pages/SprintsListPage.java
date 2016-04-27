/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import com.przemo.projectmanagementweb.controls.SprintsListPanel;
import com.przemo.projectmanagementweb.services.SprintService;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * @author Przemo
 */
public class SprintsListPage extends BasePMPage {
    
    @SpringBean
    private SprintService sprintService;
    
    public SprintsListPage(){
        add(new SprintsListPanel("sprintsList", new CompoundPropertyModel<>(sprintService.retrieveAllSprints())));
    }
}