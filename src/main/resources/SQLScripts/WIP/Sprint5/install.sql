/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-09
 */

/*
This file contains all changes from all other sql files for the release
*/

alter table users add pass text;
alter table users add active boolean;
alter table users add activated_on timestamp without time zone;
alter table users add ended_on timestamp without time zone;

create table users_activation(id serial primary key, user_id int references users(id), activation_code text, 
expires_on timestamp without time zone,
 used boolean);

create table userloginhistory(id serial primary key, user_id int references users(id), date_when timestamp without time zone not null default now(), 
ip_address varchar(15) not null,
 client_name text);

create or replace function pr_create_user(email varchar(50), rola int, pass text, code text, minutes int)
returns integer as
'insert into users(email, role, pass, active) values($1, $2, $3, false);
insert into users_activation(user_id, activation_code, expires_on) values((select currval(''users_id_seq'')), $4, now()+($5 * interval ''1 minute''));
select 1;'
language sql volatile;

create or replace function pr_last_login_for_user(user_id int)
returns timestamp without time zone as
'select max(t.date_when) from  (select date_when from userloginhistory where user_id=$1) t;'
language sql volatile;

create or replace function pr_user_login(username varchar(50), password text, userip varchar(15), client_name text)
returns integer as
'insert into userloginhistory(user_id, ip_address, client_name) values((select id from users where email=$1 limit 1), $3, $4);
select id from users where username=$1 and password=$2;'
language sql volatile;