package com.przemo.projectmanagementweb.domain;
// Generated 2016-04-20 12:30:45 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * TimeLog generated by hbm2java
 */
public class TimeLog  implements java.io.Serializable {


     private int id;
     private Task task;
     private double time;
     private Date date;

    public TimeLog() {
    }

    public TimeLog(int id, Task task, double time, Date date) {
       this.id = id;
       this.task = task;
       this.time = time;
       this.date = date;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Task getTask() {
        return this.task;
    }
    
    public void setTask(Task task) {
        this.task = task;
    }
    public double getTime() {
        return this.time;
    }
    
    public void setTime(double time) {
        this.time = time;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }




}


