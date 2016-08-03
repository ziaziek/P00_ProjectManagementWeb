/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */

create or replace function pr_create_user(email varchar(50), rola int, pass text)
returns void as
'insert into users(email, role, pass, active) values($1, $2, $3, false);'
language sql volatile