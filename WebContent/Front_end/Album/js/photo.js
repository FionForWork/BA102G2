$(document).ready(function(){
    $("#uploadbtn").click(function(){
        $("#uploadModal").modal();
    });
    
    $(".overlay").on("click",function(){
    	$(this).toggleClass("checked");
    	$(this).find("i").toggleClass("fa-4x");
    	$(this).find("span").toggle();
    	
    	var checkbox = $(this).parent().next("input[type=checkbox]");
    	var selectedLength = $('input[type=checkbox]:checked').length; 
    	if(checkbox.is(':checked')){
    		
    		$("#numberOfSelected").text(selectedLength - 1);
    		console.log("checked");
    	}else{
    		console.log("uncheck");
    		$("#numberOfSelected").text(selectedLength + 1);
    	}
    	
    });
    
    $("#selectConfirm").click(function(){
    	var selectedLength = $('input[type=checkbox]:checked').length;
    	var availableNum = $("#availableNum").text();
    	if( selectedLength === 0){
    		$("#unselectedModal").modal();
    		return;
    	}else if(selectedLength > availableNum){
    		$("#outOfAvailableModal").modal();
    		return;
    	}
    	$("#selectModal").modal();
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




