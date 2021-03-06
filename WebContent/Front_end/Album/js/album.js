$(document).ready(function(){
    $("#createAlb").click(function(){
        $("#albumModal").modal();
        $("#emptyFile").html("");
        $('input[name=name]').val(''); 
        $("#inputFile").removeClass("form-control");
    });
    
    $("#inputFile").fileinput({
        maxFileCount: 100,
        allowedFileTypes: ["image", "video"],
        language: 'zh-TW', //设置语言
        dropZoneEnabled: true,//是否显示拖拽区域
        showUpload: false,
        theme: "fa",
        
    });  
    
    $("#createBtn").click(function(){
    	if($("#inputFile").val() != ""){
    		$("#createAlbForm").submit();
    	}else{
    		$("#emptyFile").html(" (您尚未選擇照片或影片喔!)");
    	}
    	
    });
        
});


 function preview_images() {
     var total_file=document.getElementById("upload").files.length;

     for(var i = 0; i < total_file; i++){

      $('#showPanel').append("<div class='col-xs-12 col-sm-4 text-right'><img class='img-responsive preview' src='"+
      	URL.createObjectURL(event.target.files[i])+"'><span class='glyphicon glyphicon-remove'></span></div>");
     }
}
