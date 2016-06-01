/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.TimeLog;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemysław
 */
@Service
public class TimeLogService {
    
    public void saveTimeLog(TimeLog entry){
        HibernateUtil.saveObject(entry);
    }
    
    public List<TimeLog> getTimeLogs(int taskId){
        return HibernateUtil.runQuery("from TimeLog where task="+taskId);
    }
    
    public Duration getTimeLoggedForTask(int taskId){
        int minutes = (int) (((double)HibernateUtil.runQuery("select sum(tl.time) from TimeLog tl where tl.task="+taskId).get(0))*60);
        return Duration.of(minutes, ChronoUnit.MINUTES);
    }
    
    public Duration getTimeLoggedForSprint(int printId){
        return null;
    }

    public void delete(TimeLog object) {
        HibernateUtil.deleteObject(object);
    }
}
