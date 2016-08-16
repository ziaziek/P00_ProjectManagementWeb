/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-08
 */

create or replace function pr_last_login_for_user(user_id int)
returns timestamp without time zone as
'select max(date_when) from  (select date_when from userloginhistory where user_id=$1);'
language sql volatile;