/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */

create or replace function pr_user_login(username varchar(50), password text, userip varchar(15), client_name text)
returns integer as
'insert into userloginhistory(user_id, ip_address, client_name) values($1, $2, $3);
select id from user where username=$1 and password=$2 and active=1;'
language sql volatile;