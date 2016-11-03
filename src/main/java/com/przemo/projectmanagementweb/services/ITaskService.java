/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import java.util.List;

/**
 *
 * @author Przemo
 */
public interface ITaskService {

    Task createNewTask();

    List<Task> getAllTasks();

    List<Status> getAvailableStatuses();

    List<Task> getBacklogTasks();

    List<Task> getBacklogTasksForProject(Projects project);

    Task getTaskById(final int taskId);

    List getTaskTypes();

    List<Task> getTasksForProject(Projects project);

    /**
     * Builds a list of all tasks for the given sprint
     * @param sprint
     * @return
     */
    List<Task> getTasksForSprint(Sprint sprint);

    /**
     * Build a list of tasks belonging to the sprint of the given status
     * @param sprint
     * @param status
     * @return
     */
    List<Task> getTasksForSprint(Sprint sprint, Status status);

    List<Task> getUnfinishedSprintTasks(final Sprint sprint);

    int saveTask(Task t);
    
}
