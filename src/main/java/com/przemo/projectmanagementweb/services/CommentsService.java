/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.przemo.projectmanagementweb.services;

import com.przemo.projectmanagementweb.domain.TaskComments;
import com.przemo.projectmanagementweb.domain.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class CommentsService {
    
    public List<TaskComments> retrieveComments(){
        List<TaskComments> ret = new ArrayList<>();
        for(int i=0; i<3; i++){
            TaskComments tc = new TaskComments();
            tc.setComment("Lorem ipsum dolor sit amet, noster eirmod deseruisse vel at. "
                    + "Vim tollit denique nominavi no, simul interpretaris ex sit. Ei sumo viris mel. "
                    + "Eos dolore habemus id, dicam iracundia duo cu. Justo repudiare eos ut.");
            tc.setDate(new Date());
            Users u = new Users();
            u.setEmail("email_"+i+"@email.pl");
            tc.setUsers(u);
            ret.add(tc);
        }
        return ret;
    }
    
    public void saveComment(TaskComments comments){
        
    }
}
