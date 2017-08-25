
function validateForm(){
	if (!checkName($('#contactName').val())){
		return(false);	
	}
	if (!checkEmail($('#contactEmail').val())){
		return(false);	
	}
	if (!checkMessage($('input[name="messagesArea"]').val())){
		return(false);	
	}
	
	//alert("已成功送出！");
	//form.submit();
	//return false;
	doAjax();
}
function doAjax(btn){
	
	$("#ContextUsBtn").html("<i class='fa fa-spinner fa-spin'></i> SENDING...");
	$.ajax({
		url:$('input[name="path"]').val(),
		type:'POST',
		data:{
			name : $('input[name="name"]').val(),
			email : $('input[name="email"]').val(),
			messagesArea : $('input[name="messagesArea"]').val()
		},
		success:function success(){
			//alert("已成功送出！");
			$("#snackbar").addClass("show");
			setTimeout('$("#snackbar").removeClass("show")',5000);
			$("#ContextUsBtn").html("<i class='fa fa-paper-plane'></i> SEND MESSAGE");
			$('input[name="name"]').val('');
			$('input[name="email"]').val('')
			$('input[name="messagesArea"]').val('');
		},
		error:function(xhr){
			alert('Ajax request error!');
		}
	});
}
function checkEmail(email){
	index = email.indexOf ('@', 0);		// 尋找 @ 的位置，0 代表開始尋找的起始位置
	if (email.length==0) {
		alert("請輸入電子郵件地址！");
		return (false);
	} else if (index==-1) {
		alert("錯誤：必須包含「@」。");
		return (false);
	} else if (index==0) {
		alert("錯誤：「@」之前不可為空字串。");
		return (false);
	} else if (index==email.length-1) {
		alert("錯誤：「@」之後不可為空字串。");
		return (false);
	} else
		return (true);
}

function checkName(name){
	if (name.length==0) {
		alert("請輸入姓名！");
		return (false);
	} else
		return (true);
}
function checkMessage(messagesArea){
	if (messagesArea.length==0) {
		alert("請輸入內容！");
		return (false);
	} else
		return (true);	
}
