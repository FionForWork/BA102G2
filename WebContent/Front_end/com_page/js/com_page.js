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

function changeSort(a) {
	if (a == '0') {
		$("#sortCom_noDesc").show();
		$("#sortCom_no").hide();
	}
	if (a == '1') {
		$("#sortCom_no").show();
		$("#sortCom_noDesc").hide();
	}
	
	if (a == '2') {
		$("#sortScoreDesc").show();
		$("#sortScore").hide();
	}
	if (a == '3') {
		$("#sortScore").show();
		$("#sortScoreDesc").hide();
	}
}
