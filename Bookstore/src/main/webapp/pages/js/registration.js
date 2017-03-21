/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#signupbtn').click(){
    
}
function validateemail()
{
  var emailpattern=/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
var evalidtion=emailpattern.test(document.getElementById('email').value);
if(evalidtion==false)
throw "InvalidEmail";
}

function validateMobile()
{
  var mobilepattern=/^01(0|1|2){1}[0-9]{8}$/;
var mobiletest=mobilepattern.test(document.getElementById('mobile').value);
if(mobiletest==false)
throw "Invalidmobile";
}
