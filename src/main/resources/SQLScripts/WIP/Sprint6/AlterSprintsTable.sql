/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-17
 */


create table sprint_status(id serial primary key, name varchar(15) not null);

insert into sprint_status(name)
select 'Created' UNION
select 'Current' UNION
select 'Closed';

alter table sprint add sprint_status_id int references sprint_status(id);