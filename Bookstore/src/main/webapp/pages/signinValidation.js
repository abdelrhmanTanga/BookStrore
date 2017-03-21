/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


  function email(){
          var emailReg =/[a-z].*(@)[a-z]*(.)[a-z]{2,}$/;
          var validEmail = document.getElementById('email').value;
          if (emailReg.test(validEmail) === false)
          {
            throw "invalid email address";
          }
      }
      function validateEmail(){
        try{
          document.getElementById("invalidMsg").innerHTML="";
          email();
        }
        catch(err){
          document.getElementById("invalidMsg").innerHTML="invalid email address";
        }
      }
      function validatePass(){
      var passReg = /(^[a-zA-Z].*[0-9]).*$/;
      var checkPass = document.getElementById("pass").value;
      if(passReg.test(checkPass)==false)
      {
        throw "valid password required";
      }
    }
    function valPass(){
      try{
        document.getElementById("passMsg").innerHTML="";
        validatePass();
      }
      catch(err){
        document.getElementById("passMsg").innerHTML="incorrect password";
      }
    }
     function mobile(){
        var mobileReg= /^01[0-2][0-9]{8}$/;
        var validMobile =document.getElementById("mob").value;
        if(mobileReg.test(validMobile) == false)
        {
          throw "invalid mob";
        }
      }
      function validateMobile(){
        try{
            document.getElementById("invalidMob").innerHTML="";
            mobile();
        }
        catch(err){
          document.getElementById("invalidMob").innerHTML="invalid mobile number";
        }
      }
      
       function emailSignup(){
          var emailReg =/[a-z].*(@)[a-z]*(.)[a-z]{2,}$/;
          var validEmail = document.getElementById('form-email').value;
          if (emailReg.test(validEmail) === false)
          {
            throw "invalid email address";
          }
      }
      function validateEmailSignup(){
        try{
          document.getElementById("invalidMsg2").innerHTML="";
          email();
        }
        catch(err){
          document.getElementById("invalidMsg2").innerHTML="invalid email address";
        }
      }
      
       function validatePassSignup(){
      var passReg = /(^[a-zA-Z].*[0-9]).*$/;
      var checkPass = document.getElementById("pass").value;
      if(passReg.test(checkPass)==false)
      {
        throw "valid password required";
      }
    }
    function valPassSignup(){
      try{
        document.getElementById("passMsg2").innerHTML="";
        validatePass();
      }
      catch(err){
        document.getElementById("passMsg2").innerHTML="incorrect password";
      }
    }
    
      function submitSignup(){
          validateEmailSignup();
          valPassSignup();
          validateMobile();
      }
    function submitLogin(){
      validateEmail();
      valPass();
      
    }