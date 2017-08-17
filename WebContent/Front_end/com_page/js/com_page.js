function change(c) {
	if (c == '1') {
		document.getElementById("more_works").style.display = 'block';
		document.getElementById("more_works_btn").style.display = 'none';
	}
	if (c == '2') {
		document.getElementById("more_services").style.display = 'block';
		document.getElementById("more_services_btn").style.display = 'none';
	}
}

function validateForm(form){
	if (!checkName(form.name.value)){
		return(false);	
	}
	if (!checkEmail(form.email.value)){
		return(false);	
	}
	if (!checkMessage(form.messagesArea.value)){
		return(false);	
	}
	form.submit();
	return(true);
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
