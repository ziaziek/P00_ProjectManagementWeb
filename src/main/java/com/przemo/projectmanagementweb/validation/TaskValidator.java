/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.validation;

import com.przemo.projectmanagementweb.domain.Task;

/**
 *
 * @author Przemo
 */
public class TaskValidator {
    
    private final Task task;
    
    public TaskValidator(Task task){
        this.task=task;
    }
    /**
     * 
     * @return 
     */
    public boolean validate(){
        return task!=null && !(task.getSprint()!=null &&
                task.getStatus().getName().equals("Created"));
    }
}
