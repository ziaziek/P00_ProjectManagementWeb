/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

/**
 *
 * @author Przemo
 */
public class ServiceUtil {
    
    private static TaskService taskService;

    public static TaskService getTaskService() {
        return taskService;
    }

    public static CommentsService getCommentsService() {
        return commentsService;
    }

    public static SprintService getSprintService() {
        return sprintService;
    }
    private static CommentsService commentsService;
    private static SprintService sprintService;
    
    static {
        taskService = new TaskService();
        commentsService = new CommentsService();
        sprintService = new SprintService();
    }
    
}
