	$( function() {
	
	    $( ".draggable" ).draggable({scope:"calendar",containment: "#box",revert: true,
	            drag:function (event, ui) {
	                $("#dragid").val($(this).attr("id"));
	                $("#thisDate").val($(this).parent('td').attr("id").replace(/-/g,""));
	            }
	        });
	
	    $(".calendar").droppable({
	            scope: "calendar",            
	            drop: function (event, ui) {
	                $(this).css("background-color", "lightgreen");
	                $("#dropid").val($(this).attr("id"));
	                $("#toDate").val($(this).attr("id").replace(/-/g,""));
	                changeSchedule();
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
		$("#date").val($(obj).attr("id"));
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
	  	var thisDate = $(y).parent('div').parent('td').attr("id").replace(/-/g,"");
	  	$('#cal_no').val(j);
	  	$('#thisDate').val(thisDate);
	  	onDeleteSchedule();
		$('#deleteForm').submit();
	  }