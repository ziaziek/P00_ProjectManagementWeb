/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-12
 */

create or replace function pr_activate_user(code text)
returns boolean as
'update users set active=true where id=(select user_id from users_activation where activation_code=$1 and used=false and expires_on>now());
update users_activation set used=true where activation_code=$1;
select coalesce(active, false) from users where id=(select user_id from users_activation where activation_code=$1);
'
language sql volatile;