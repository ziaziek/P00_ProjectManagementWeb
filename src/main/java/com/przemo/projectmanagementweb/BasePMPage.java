/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public class BasePMPage extends WebPage {
    
    public BasePMPage(){
        super();
        initPage();
    }
    
    public BasePMPage(IModel model){
        super(model);
        initPage();
    }
    private void initPage(){
        add(new Link("homeButton"){
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
           
        });
    }
}
