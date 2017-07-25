$(document).ready(function(){
    $("#uploadbtn").click(function(){
        $("#uploadModal").modal();
    });
    

});
function preview_images() {
     var total_file=document.getElementById("upload").files.length;

     for(var i = 0; i < total_file; i++){

      $('#showPanel').append("<div class='col-xs-12 col-sm-4 text-right'><img class='img-responsive preview' src='"+
        URL.createObjectURL(event.target.files[i])+"'><span class='glyphicon glyphicon-remove'></span></div>");
     }
}



