package com.przemo.projectmanagementweb.domain;
// Generated 2016-04-20 12:30:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {


     private int id;
     private String name;
     private Set userses = new HashSet(0);

    public Role() {
    }

	
    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role(int id, String name, Set userses) {
       this.id = id;
       this.name = name;
       this.userses = userses;
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
    public Set getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set userses) {
        this.userses = userses;
    }




}

