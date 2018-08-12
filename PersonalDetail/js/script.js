
$('#registration').submit(function(e) {
    e.preventDefault();
   
    var email = $('#email').val();
    var confirm_Email = $('#confirmEmail').val();
    var phone_No = $('#phoneNo').val();

    $(".error").remove();

    
    if (email.length > 1) {
      var regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/;
      var validEmail = regEx.test(email);
      if (!validEmail) {
        $('#email').after('<span class="error">Enter a valid email</span>');
      }
    }    
     
    if(email!=confim_Email){
        $('#confirmEmail').after('<span class="error">Enter same as primary email</span>')
      }   


    if (phone_No.length < 10) {
      $('#password').after('<span class="error">Phone No must be 10 digit number</span>');
    }else {
      var regEx = [0-9];
      var validPhone = regEx.test(phone_No);
      if(!validPhone) {
        $('#phoneNo').after('<span clas="error> Enter a valid phoneNo</span>');
      }
    }
  });

});
