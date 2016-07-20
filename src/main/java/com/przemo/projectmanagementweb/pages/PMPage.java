/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.pages;

import org.apache.wicket.model.IModel;

/**
 *
 * @author Przemo
 */
public abstract class PMPage extends BasePMPage {
    
    
    protected PMPage(){
        super();
    }
    
    protected PMPage(IModel model){
        super(model);
    }
 
}
