/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.services.ITaskService;
import com.przemo.projectmanagementweb.services.SprintService;

/**
 *
 * @author Przemo
 */
public class SprintServiceStub extends SprintService {
    
    protected void setTaskService(ITaskService taskService) {
        this.taskService = taskService;
    }
    public boolean isSprintCloseable(final Sprint sprint){
        return super.isSprintIsCloseable(sprint);
    }
}
