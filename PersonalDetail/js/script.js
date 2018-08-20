
$("form").submit(function(){
  console.log("Hello");
     
    var email = $('#email').val();
    console.log(email);
    var confirm_Email = $('#confirmEmail').val();
    var phone_No = $('#phoneNo').val();

    $(".error").remove();

    if (email.length > 1) {
      var regEx = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      var validEmail = regEx.test(email);
      if (!validEmail) {

        $('#email').after('<span class="error">Enter a valid email</span>');
              return false;
      }
    }

    if(email!=confim_Email){
        $('#confirmEmail').after('<span class="error">Enter same as primary email</span>')

      }

    if (phone_No.length < 10) {
      $('#phoneNo').after('<span class="error">Phone No must be 10 digit number</span>');

    } else {
      var regEx = [0-9];
      var validPhone = regEx.test(phone_No);
      if(!validPhone) {
        $('#phoneNo').after('<span clas="error> Enter a valid phoneNo</span>');

      }

    }
/*
  $.ajax({

    url : "https://reqres.in/api/users/10",
    async: false,

    dataType: 'json',

    beforeSend:function(){

      document.write("loading...")
    },

    type: "GET",
    success: function(data){
      $('#avatar').attr('src',data['data']['avatar'])
      $('#firstname').val(data['data']['avatar'])
      $('#lastname').val(data['data']['avatar'])
      console.log(data['data']['avatar']);

    },
    error: function(er){
      console.log(er);

    }
  });*/
});

