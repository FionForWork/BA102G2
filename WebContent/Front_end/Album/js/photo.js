$(document).ready(function(){
    $("#uploadbtn").click(function(){
        $("#uploadModal").modal();
    });
    
//   $("#deletebtn").click(function(){
//	   $.post($('input[name = path]').val(),
//		        {
//		          action: "delete_Content",
//		          cont_no: $('input[name = cont_no]').val()
//		        });
//   });
   
//   $("#setCover").click(function(){
//	   $.post($('input[name = path]').val(),
//		        {
//		          action: "setCover",
//		          cont_no: $('input[name = cont_no]').val(),
//		          alb_no: $('input[name = alb_no]').val()
//		        });
//   });
});
function preview_images() {
     var total_file=document.getElementById("upload").files.length;

     for(var i = 0; i < total_file; i++){

      $('#showPanel').append("<div class='col-xs-12 col-sm-4 text-right'><img class='img-responsive preview' src='"+
        URL.createObjectURL(event.target.files[i])+"'><span class='glyphicon glyphicon-remove'></span></div>");
     }
}



