/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import java.util.List;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class TaskService {

    public Task getTaskById(final int taskId){
        return (Task) HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users join fetch t.status where t.id="+taskId).get(0);
    }
    
    public List<Task> getAllTasks() {
        return HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users");
    }

    /**
     * Builds a list of all tasks for the given sprint
     * @param sprint
     * @return 
     */
    public List<Task> getTasksForSprint(Sprint sprint) {
        return HibernateUtil.runQuery("select t from Task t join fetch t.sprint left join fetch t.users  where t.sprint.id="+sprint.getId());
    }
    
    /**
     * Build a list of tasks belonging to the sprint of the given status
     * @param sprint
     * @param status
     * @return 
     */
    public List<Task> getTasksForSprint(Sprint sprint, Status status){
        return HibernateUtil.runQuery("select t from Task t join fetch t.sprint left join fetch t.users join fetch t.status where t.sprint.id="+sprint.getId()+
                " and t.status.id="+status.getId());
    }
    
    public List<Task> getBacklogTasks(){
        return HibernateUtil.runQuery("select t from Task t where t.sprint=null");
    }

    public void saveTask(Task t) {
        HibernateUtil.saveObject(t);
    }

    public List<Status> getAvailableStatuses() {
        return HibernateUtil.runQuery("from Status");
    }

    public List getTaskTypes() {
        return HibernateUtil.runQuery("from TaskType");
    }
}
    
