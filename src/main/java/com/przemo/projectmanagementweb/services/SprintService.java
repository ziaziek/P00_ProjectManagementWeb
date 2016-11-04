/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.SprintStatus;
import com.przemo.projectmanagementweb.domain.Task;
import com.przemo.projectmanagementweb.services.errors.SprintServiceSaveException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class SprintService {

    @Autowired
    protected ITaskService taskService;

    public List<Sprint> retrieveAllSprints() {
        return HibernateUtil.runQuery("from Sprint s left join fetch s.sprintStatus");
    }

    public Sprint getSprint(final int id) {
        return (Sprint) HibernateUtil.runQuery("select s from Sprint s left join fetch s.sprintStatus where s.id=" + id).get(0);
    }

    public void saveSprint(Sprint object) throws Exception {
        if(object.getSprintStatus()==null){
            object.setSprintStatus(getAllStatuses().stream().filter(s->s.getName().equals("Created")).collect(Collectors.toList()).get(0));
        }
        HibernateUtil.saveObject(object);
    }

    public BigInteger getAvailableTime(Sprint s) {
        List<BigInteger> l = HibernateUtil.runSQLQuery("select sum(estimated_time) from task where sprint=" + s.getId());
        if (!l.isEmpty() && l.get(0) != null) {
            return l.get(0);
        } else {
            return new BigInteger("0");
        }
    }

    public List<SprintStatus> getAllStatuses() {
        return HibernateUtil.runQuery("from SprintStatus");
    }

    public long getNumberOfSprintsForStatus(String statusName) {
        return (long) HibernateUtil.runQuery("select count(*) from Sprint where sprintStatus.name='" + statusName + "'").get(0);
    }

    public void saveSprintWithStatus(final Sprint sprintObject, final String status) throws SprintServiceSaveException {

        if ("Current".equals(status) && getNumberOfSprintsForStatus("Current") > 0) {
            throw new SprintServiceSaveException("Current sprint already exists.");
        } else {
            if ("Closed".equals(status) && !isSprintIsCloseable(sprintObject)) {
                throw new SprintServiceSaveException("The sprint contains tasks that haven't been finished.");
            } else if (sprintObject.getSprintStatus() == null) {
                sprintObject.setSprintStatus(getAllStatuses().stream().filter(s -> s.getName().equals("Created")).findAny().get());
            }
        }
        sprintObject.setSprintStatus(getAllStatuses().stream().filter(s -> s.getName().equals(status)).findFirst().get());
        try {
            saveSprint(sprintObject);
        } catch (Exception ex) {
            LoggerFactory.getLogger(getClass()).error(ex.getMessage());
            throw new SprintServiceSaveException("Sprint could not be saved due to internal error. ");
        }
    }

    protected boolean isSprintIsCloseable(final Sprint sprintObject) {
        List<Task> tlist = taskService.getUnfinishedSprintTasks(sprintObject);
        return (sprintObject != null && tlist.isEmpty());
    }
}
