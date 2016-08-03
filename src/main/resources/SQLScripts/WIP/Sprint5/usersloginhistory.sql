/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */


create table userloginhistory(id serial primary key, user_id int references users(id), date_when timestamp without time zone not null default now(), ip_address varchar(15) not null,
 client_name text);