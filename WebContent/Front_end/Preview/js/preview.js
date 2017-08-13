		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		var xPoints = [];
		var yPoints = [];
		var image;
	
	// 預覽圖片	
	function load_image(){
			 
		var input;
		input = document.getElementById('imgfile');
		input.click();
	}
	
	function preview_images() {
		image = new Image();
	    image.onload = function() {
	        context.drawImage(image,0,0,image.width,image.height);
	     };
	     var total_file=document.getElementById("imgfile").files.length;
	     console.log(context);
	     for(var i = 0; i < total_file; i++){
	         if(event.target.files[i].type.match('image.*')){
	            image.setAttribute("src",URL.createObjectURL(event.target.files[i]));
	            image.setAttribute("id","imagePreview");
	            console.log(image);
	    		image.addEventListener("mousewheel",wheelImage,false);
	    		console.log(image);
	         }
	     }
	}
	// 滑鼠滾動事件處發圖片縮放效果
	function wheelImage(e){
		var width = parseInt(window.getComputedStyle(this).width);
		var height = parseInt(window.getComputedStyle(this).height);
		var zoom = 40;
		
		if(e.wheelDelta > 0){
			this.style.width = Math.min(1500,width + zoom) + "px";
			this.style.height = Math.min(1500,height + zoom) + "px";
		}else{
			this.style.width = Math.max(200,width - zoom) + "px";
			this.style.height = Math.max(200,height - zoom) + "px";
		}
		e.preventDefault();
	}
	
		// 校準滑鼠座標	
		function  getMousePos(canvas, evt) {
          var rect = canvas.getBoundingClientRect(), // abs. size of element
              scaleX = canvas.width / rect.width,    
              scaleY = canvas.height / rect.height;  

          return {
            x: (evt.clientX - rect.left) * scaleX,   // scale mouse coordinates after they have
            y: (evt.clientY - rect.top) * scaleY     // been adjusted to be relative to element
          }
        }
		
		function clearDrawing(){
			context.clearRect(0, 0, canvas.width, canvas.height);
		}
		
		
		$(document).ready(function(e) {
			
			var stillDown = false;
			var canvas = $("#canvas");
			
			// Drawing Effect
			$("#drawBtn").on("click",function(e){
				console.log("1111111111");
				canvas.css("cursor","crosshair");
				canvas.on("mousedown", function(e) {
					stillDown = true;
					console.log("down");
                    var pos = getMousePos(document.getElementById("canvas"), e);
					context.beginPath();
                    console.log("click" + pos.x + "," +  pos.y)
					context.moveTo(pos.x, pos.y);

				});
				canvas.on("mousemove", function(e) {
					if (!stillDown) {
						return;
					}
					console.log("moving");
					context.lineWidth = 10;
					context.strokeStyle = "green";
					context.lineCap = 'round';
                    var pos = getMousePos(document.getElementById("canvas"), e);
					context.lineTo(pos.x, pos.y);
					xPoints.push(pos.x);
					yPoints.push(pos.y);
					context.stroke();

				});
				canvas.on("mouseup", function(e) {
					stillDown = false;
					context.closePath();
				});
			});
			
			$("#submitBtn").on("click", function() {
				var JSONxPoints = JSON.stringify(xPoints);
				var JSONyPoints = JSON.stringify(yPoints);
				$("#xPoints").val(JSONxPoints);
				$("#yPoints").val(JSONyPoints);
				console.log("XXX");
				$("#image-form").submit();
				console.log("YYY");
			});
			
		});