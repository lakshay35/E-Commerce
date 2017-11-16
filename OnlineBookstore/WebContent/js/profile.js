$(document).ready(function() {
  $.ajax({
    method : "get",
    url : "BookstoreServlet",
    data : {
      "viewProfile" : "viewProfile"
    }, dataType : "json",
    success : function(responseText) {
      $("#fname").val(responseText.fname);
      console.log(responseText);
      $("#lname").val(responseText.lname);
      $("#email").val(responseText.email);
      $("#phone").val(responseText.phone);
    },
    error : function() {
      alert("error occured");
    }
  });
});
