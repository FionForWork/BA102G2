  $( function() {

	    $( ".draggable" ).draggable({ snap: ".ui-widget-head",scope:"calendar",
	            drag:function (event, ui) {
	                $("#dragid").val($(this).attr("id"));
	            }
	        });

	    $(".calendar").droppable({
	            scope: "calendar",            
	            drop: function (event, ui) {
	                $(this).css("background-color", "lightgreen");
	                $("#dropid").val($(this).attr("id"));
	                $("#updateDateForm").submit();
	            },
	            over: function (event, ui) {
	                $(this).css("background-color", "lightgreen")
	            },
	            out: function (event, ui) {
	                $(this).css("background-color", "")
	            }
	        });
	  } );
  
  function change(){
		
		$('#changeCalendar').submit();
	}
  
  function add(y){
	  var obj = $(y);
		$("#test").val($(obj).attr("id"));
		$("#datepicker").val($(obj).attr("id"));
	}
  $( function() {
	    $( "#datepicker" ).datepicker({dateFormat: 'yy-m-dd'});
	  } );
  
  function show(y){
	$(y).children("button").show();
  }
  
  function hide(y){
		$(y).children("button").hide();
	  }
  
  function deleteSchedule(y){
	  	var j = $(y).parent('div').attr("id");
	  	$('#cal_no').val(j);
		$('#deleteForm').submit();
	  }