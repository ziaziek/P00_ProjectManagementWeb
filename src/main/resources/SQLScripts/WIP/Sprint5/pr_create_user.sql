/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */

create or replace function pr_create_user(email varchar(50), rola int, pass text, code text, int minutes)
returns void as
'insert into users(email, role, pass, active, activation_code) values($1, $2, $3, false);
insert into account_activation(user_id, activation_code) values($4, now()+($5 * interval "1 minute))"'
language sql volatile