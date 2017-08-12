	
		
		function insertComtra(){
			
			var mem_no = $('input[name="mem_no"]').val();
			if(mem_no.length == 0){
				
				$("#snackbar").addClass("show");
				setTimeout('$("#snackbar").removeClass("show")',5000);
				
			}else{
				$.ajax({
					url:$('input[name="path"]').val(),
					type:'POST',
					data:{
						com_no : $('input[name="com_no"]').val(),
						mem_no : $('input[name="mem_no"]').val(),
						action : "insert_ComTra"
					},
					success:function success(){
						$("#comTracking").html("<a href='#' onclick='deleteComtra()'><i class='fa fa-heart' style='color:deeppink'> 取消收藏</i></a>");
					},
					error:function(xhr){
						alert('Ajax request error!');
					}
				});
			}
		};
		
		function deleteComtra(){
				$.ajax({
					url:$('input[name="path"]').val(),
					type:'POST',
					data:{
						com_no : $('input[name="com_no"]').val(),
						mem_no : $('input[name="mem_no"]').val(),
						action : "delete_ComTra_FromComPage"
					},
					success:function success(){
						$("#comTracking").html("<a href='#' onclick='insertComtra()'><i class='fa fa-heart-o'> 加入收藏</i></a>");
					},
					error:function(xhr){
						alert('Ajax request error!');
					}
				});
		};