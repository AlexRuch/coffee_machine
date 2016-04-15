/**
 * Created by alexey on 05/04/16.
 */
//$(document).ready(function(){
//    if (document.getElementById("userMoney") < document.getElementById("orderCost")){
//        alert("Not enough money!");
//    }
//    else {
//        alert(" enough money!");
//    }
//});
    function checkMoney(){
var userMoney = document.getElementById("userMoney").innerHTML;
var orderCost = document.getElementById("orderCost").innerHTML;
    if(orderCost > userMoney){
        alert("Not enough money!");
    }
}