<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery연결 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- jquery UI연결 -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js'></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>
<script type="text/javascript" src="/resources/js/thmUpload.js"></script>
</head>
<body>
<form role='form' action="create" method="post">
	<table border=1>
    	<tr><td>제목</td><td><input type="text" name="title"></td></tr>
	    <tr><td>내용</td><td><textarea name="text" cols="50" rows="30" maxlength="2000"></textarea></td></tr>
	</table>
	<input type="submit" value="글쓰기">
</form>
<div class="rowi"><!-- row -->
		<h2>File Attach</h2>
		<div>
			<div class='uploadDiv'>
				<input type='file' name='uploadFile' multiple>
				<button class='ucBtn'>X</button>
			</div>
		</div>	
		<div class='uploadResult'>
			<ul>
			</ul>
		</div>  
</div><!-- row -->
<div class="row">
		<h2>Thumbnail</h2>
		<div>
			<div class='tuploadDiv'>
				<input type='file'  name='tuploadFile'>
				<button class='ucBtn'>X</button>
			</div>
		</div>	
		<div class='tuploadResult'>
			<ul>
			</ul>
		</div>  
</div>
</body>
</html>