	function pay(y){
		$('#res_no').val($(y).attr("id"));
	}
	
	
	function rating(y){
		$('#res_no_rating').val($(y).attr("id"));
		$('#ratingForm').submit();
	}