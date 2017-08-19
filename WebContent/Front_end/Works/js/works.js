$(document).ready(function(){
    $("#uploadbtn").click(function(){
        $("#uploadModal").modal();
    });
    

//    // lightbox img
//    $(".aa").each(function(){
//    	$(this).click(function(){
//    		$("#lightboxImgModal").css("display","block");
//    		$("#lightboxImg").attr("src",this.src);
//    		
//    	});
//    });
// 
//    // close lightbox img
//    $(".closeImg").each(function(){
//    	$(this).click(function(){
//        	$("#lightboxImgModal").css("display","none");
//        	
//        });
//    });
//    $("#lightboxImgModal").on("click",function(){
//    	$(this).css("display","none");
//    });

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

function openLightBox(img){
	// lightbox img
    var src = $(img).attr("src");
    $("#lightboxImgModal").css("display","block");
    $("#lightboxImg").attr("src",src);
    $("#lightboxImgModal").on("click",function(){
    	$(this).css("display","none");
    });
}
function closeLightBox(){
	// close lightbox img
    
    $("#lightboxImgModal").css("display","none");
    
}


