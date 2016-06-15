/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Przemo
 * Created: 2016-06-09
 */

CREATE OR REPLACE FUNCTION pr_get_hours_for_sprint("sprintID" integer)
  RETURNS double precision AS
'select coalesce(sum(t.time),0) as "time" from time_log t join task ts on ts.id=t.task where ts.sprint = $1;'
  LANGUAGE sql VOLATILE
  COST 100;