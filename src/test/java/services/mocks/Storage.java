/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.mocks;

import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.Status;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.domain.TaskType;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class Storage {
    
    public static Set<Task> getTasks(){
        Set<Task> tasks = new HashSet<>();
        Task t = new Task();
        TaskType tp = new TaskType(1, "Feature");
        t.setId(1);
        t.setTaskType(tp);
        t.setTitle("Task 1");
        t.setDescription("Description");
        t.setStatus(new Status(1, "To Do"));
        //t.setSprint(getCurrentSprint());
        tasks.add(t);
        return tasks;
    }
    
    public static Sprint getCurrentSprint(){
        Sprint s = new Sprint(1, "Active Sprint", null, null, 16);
        s.setTasks(getTasks());
        return s;
    }
}
