/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class SprintStatus implements java.io.Serializable {
    
    private int id;

    public SprintStatus(){
        
    }
    
    public SprintStatus(int id, String name){
        this.id=id;
        this.name=name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }
    private String name;
    Set<Sprint> sprints = new HashSet(0);
}
