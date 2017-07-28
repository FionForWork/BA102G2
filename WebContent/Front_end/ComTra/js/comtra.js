	$("document").ready(function(){
		$("#insertComTra").click(function(){
			$.ajax({
				url:$('input[name="path"]').val(),
				type:'POST',
				data:{
					com_no : $('input[name="com_no"]').val(),
					mem_no : $('input[name="mem_no"]').val(),
					action : "insert_ComTra"
				},
				success:function success(){
					$("#snackbar").addClass("show");
					console.log("XXXXXXX");
					setTimeout('$("#snackbar").removeClass("show")',5000);
					console.log("@@@@@@@@@@");
				},
				error:function(xhr){
					alert('Ajax request error!');
				}
			});
			
		});
	});