<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>She Said Yes!</title>
    <meta charset="UTF-8">
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/earlyaccess/notosanstc.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Hind" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Sacramento" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,600,400italic,700' rel='stylesheet' type='text/css'>
    <link href="css/bootstrap-multiselect.css" rel="stylesheet">
    <link href="https://cdn.weddingday.com.tw/weddingday-file/v2/css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/pnotify.custom.min.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/top.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
  <h2>Modal Example</h2>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">檢舉</h4>
        </div>
        <div class="modal-body">
        <label for="inputdefault">檢舉對象</label>
         <input class="form-control" id="inputdefault" type="text" name="reproted_no">
        </div>
        <div class="modal-body">
        <label for="inputdefault">標題</label> 
        <input class="form-control" id="inputdefault" type="text" name="title">
        </div>
        <div class="modal-body">
        <label for="inputdefault">內容</label>
         <input class="form-control" id="inputdefault" type="text" name="content">
        </div>
        <div class="modal-footer">
        	<input	type="submit" class="btn btn-info" value="檢舉">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>

</body>
</html>