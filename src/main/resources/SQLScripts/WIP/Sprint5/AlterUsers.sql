/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-08-03
 */

alter table users add pass text;
alter table users add active boolean;
alter table users add activated_on timestamp without time zone;
alter table users add ended_on timestamp without time zone;