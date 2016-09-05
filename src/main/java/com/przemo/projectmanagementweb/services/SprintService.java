/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.HibernateUtil;
import com.przemo.projectmanagementweb.domain.Sprint;
import com.przemo.projectmanagementweb.domain.SprintStatus;
import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Przemo
 */
@Service
public class SprintService {

    public List<Sprint> retrieveAllSprints() {
        return HibernateUtil.runQuery("from Sprint s left join fetch s.sprintStatus");
    }

    public Sprint getSprint(final int id) {
        return (Sprint) HibernateUtil.runQuery("select s from Sprint s left join fetch s.sprintStatus where s.id=" + id).get(0);
    }

    public void saveSprint(Sprint object) throws Exception {
        if (object.getSprintStatus() != null && object.getSprintStatus().getName().equals("Current") && getNumberOfSprintsForStatus("Current") > 0) {
            throw new Exception("Current sprint already exists.");
        } else {
            if (object.getSprintStatus() == null) {
                object.setSprintStatus(getAllStatuses().stream().filter(s -> s.getName().equals("Created")).findAny().get());
            }
            HibernateUtil.saveObject(object);
        }
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

    public int getNumberOfSprintsForStatus(String statusName) {
        return (int) HibernateUtil.runQuery("select count(*) from Sprint where sprintStatus.id=" + getAllStatuses().stream().filter(s -> s.getName().equals("Current")).findAny().get().getId()).get(0);
    }
}
