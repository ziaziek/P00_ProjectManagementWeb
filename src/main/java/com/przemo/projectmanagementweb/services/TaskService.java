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
import com.przemo.projectmanagementweb.domain.Users;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Przemo
 */
public class TaskService {

    public Task getOneTask(final String title) {
        Task t = new Task();
        t.setDescription("Task description");
        t.setEstimatedTime(2);
        t.setTitle("Task title " + title);
        t.setUsers(new Users(1, null, "task@task.pl", null, null));
        return t;
    }

    public List<Task> getAllTasks() {
        return HibernateUtil.runQuery("select t from Task t left join fetch t.sprint left join fetch t.users");
    }

    public List<Task> getTasksForSprint(Sprint sprint) {
        return HibernateUtil.runQuery("select t from Task t join fetch t.sprint left join fetch t.users where t.sprint.id="+sprint.getId());
    }
    
    public List<Task> getBacklogTasks(){
        return HibernateUtil.runQuery("select t from Task t where t.sprint=null");
    }

    public void saveTask(Task t) {
        HibernateUtil.saveObject(t);
        Transaction tx = null;
    }

    public List<Status> getAvailableStatuses() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Status> ret = s.createQuery("from Status").list();
        s.close();
        return ret;
    }

    public List getTaskTypes() {
        return HibernateUtil.runQuery("from TaskType");
    }
}
