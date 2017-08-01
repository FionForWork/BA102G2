$(document).ready(function(){
    $("#uploadbtn").click(function(){
        $("#uploadModal").modal();
    });
    
 
});
function preview_images() {
	
     var total_file=document.getElementById("upload").files.length;
     
     for(var i = 0; i < total_file; i++){
    	 if(event.target.files[i].type.match('image.*')){
    		 $('#showPanel').append("<div class='col-xs-6 col-sm-3 col-md-3'><span class='glyphicon glyphicon-remove'></span><img class='img-responsive' src='"+
    		URL.createObjectURL(event.target.files[i])+"'></div>");
    	 }
      
     }
}




