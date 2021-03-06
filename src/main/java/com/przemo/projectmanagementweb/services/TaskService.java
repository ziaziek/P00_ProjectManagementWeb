/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Projects;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.validation.TaskValidator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class TaskService implements ITaskService {

    @Override
    public Task createNewTask(){
        Task t = new Task();
        t.setStatus(getAvailableStatuses().stream().filter(o->o.getName().equals("Created")).collect(Collectors.toList()).get(0));
        return t;
    }
    
    @Override
    public Task getTaskById(final int taskId){
        return (Task) HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users join fetch t.status where t.id="+taskId).get(0);
    }
    
    @Override
    public List<Task> getAllTasks() {
        return HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users");
    }

    /**
     * Builds a list of all tasks for the given sprint
     * @param sprint
     * @return 
     */
    @Override
    public List<Task> getTasksForSprint(Sprint sprint) {
        return HibernateUtil.runQuery("select t from Task t join fetch t.sprint left join fetch t.users  where t.sprint.id="+sprint.getId());
    }
    
    /**
     * Build a list of tasks belonging to the sprint of the given status
     * @param sprint
     * @param status
     * @return 
     */
    @Override
    public List<Task> getTasksForSprint(Sprint sprint, Status status){
        return HibernateUtil.runQuery("select t from Task t join fetch t.sprint left join fetch t.users join fetch t.status where t.sprint.id="+sprint.getId()+
                " and t.status.id="+status.getId());
    }
    
    @Override
    public List<Task> getTasksForProject(Projects project){
        return HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users where t.projects.id="+project.getId());
    }
    
    @Override
    public List<Task> getBacklogTasks(){
        return HibernateUtil.runQuery("select t from Task t where t.sprint=null");
    }
    
    @Override
    public List<Task> getBacklogTasksForProject(Projects project){
        return HibernateUtil.runQuery("select t from Task t where t.sprint=null and t.projects.id="+project.getId());
    }
    
    @Override
    public List<Task> getUnfinishedSprintTasks(final Sprint sprint){
        return HibernateUtil.runQuery("from Task where sprint.id="+sprint.getId()+" and status.name!='Closed'");
    }

    @Override
    public int saveTask(Task t) {
        if(new TaskValidator(t).validate()){
            HibernateUtil.saveObject(t);
            return 0;
        }
        return -1;
    }

    @Override
    public List<Status> getAvailableStatuses() {
        return HibernateUtil.runQuery("from Status");
    }

    @Override
    public List getTaskTypes() {
        return HibernateUtil.runQuery("from TaskType");
    }
    
}
    
