/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-04
 */

create table users_activation(id serial primary key, user_id int references users(id), activation_code text, 
expires_on datetime without time zone,
 used boolean);