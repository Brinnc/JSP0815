<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">

	function loginCheck() {
		$("#form1").attr({
			action: "/member/login",
			method:"post"
		});
		$("#form1").submit();
		
	}
	
	//문서가 로드되면, DOM들을 접근할 수 있음
	$(function() {
		//전송 버튼을 누르면..
		$("input[type='button']").click(function() {
			loginCheck();
		});
	});
	
</script>
</head>

<body>

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
    
    <input type="text" name="id" placeholder="Your ID..">

    <input type="password" name="pass" placeholder="Your PW..">

    <input type="button" value="전송">
    
  </form>
</div>

</body>
</html>
    