/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */

create or replace function pr_user_login(userid int, userip varchar(15), client_name text)
returns void as
'insert into userloginhistory(user_id, ip_address, client_name) values($1, $2, $3);'
language sql volatile