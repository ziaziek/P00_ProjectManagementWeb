/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function goback() {
    window.history.back();
}

$(function () {
    $(".datefield").datepicker({dateFormat: "yy-mm-dd"});
}
);