	function pay(y){
		$('#res_no').val($(y).attr("id"));
	}
	
	function resCompleted(y){
		$('#res_no_completed').val($(y).attr("id"));
		$('#resCompletedForm').submit();
	}
	
	function rating(y){
		$('#res_no_rating').val($(y).attr("id"));
		$('#ratingForm').submit();
	}