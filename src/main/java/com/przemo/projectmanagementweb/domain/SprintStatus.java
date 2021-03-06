package com.przemo.projectmanagementweb.domain;
// Generated 2016-08-22 15:22:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * SprintStatus generated by hbm2java
 */
public class SprintStatus  implements java.io.Serializable {


     private int id;
     private String name;
     private Set sprints = new HashSet(0);

    public SprintStatus() {
    }

	
    public SprintStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public SprintStatus(int id, String name, Set sprints) {
       this.id = id;
       this.name = name;
       this.sprints = sprints;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getSprints() {
        return this.sprints;
    }
    
    public void setSprints(Set sprints) {
        this.sprints = sprints;
    }




}


