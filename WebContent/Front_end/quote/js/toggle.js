function toggle(y){

    var obj = $(y);
    
    if(y.checked && $(obj).attr("id") == '0001'){
        $('#form1').show();
    }else if(y.checked == false && $(obj).attr("id") == '0001'){
        $('#form1').hide();
    }

    if(y.checked && $(obj).attr("id") == '0002'){
        $('#form2').show();
    }else if(y.checked == false && $(obj).attr("id") == '0002'){
        $('#form2').hide();
    }

    if(y.checked && $(obj).attr("id") == '0003'){
        $('#form3').show();
    }else if(y.checked == false && $(obj).attr("id") == '0003'){
        $('#form3').hide();
    }

}