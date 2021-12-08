<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jquery연결 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- jquery UI연결 -->
<script src='//cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js'></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/update.js"></script>
<script type="text/javascript" src="/resources/js/thmUpdate.js"></script>
</head>
<body>
	<form role='form' action="update" method="post">
		<table border=1>
	    	<tr><input type="hidden" name="bno" id="Bno" value="${update.bno}"></tr>
	    	<tr><td>제목</td><td><input type="text" name="title" value="${update.title}"></td></tr>
		    <tr><td>내용</td><td><textarea name="text" cols="50" rows="30" maxlength="2000">${update.text}</textarea></td></tr>
	    	<tr><td>작성자</td><td>${update.writer}</td></tr>
			<tr><td>작성일자</td><td>${update.date}</td></tr>
		</table>
		<input type="submit" id="update" value="수정하기">
	
		<div>
			<h2>파일 업로드</h2>	
			<div class='uploadDiv'>
				<input type='file'  name='uploadFile' multiple>
			</div>
			<div class='uploadResult'>
				<ul>
				</ul>
			</div>  
		</div>
		
		<div>
			<h2>썸네일</h2>
			<div class='tuploadDiv'>
				<input type='file'  name='tuploadFile'>
			</div>
			<div class='tuploadResult'>
				<ul>
					<li>
						<input type="hidden" id="ofn" value="${update.t}">
						<span id="txt"></span>
						<input type=button id="uBtn" value="X">
					</li>
				</ul>
			</div>  
		</div>
	</form>
<script type="text/javascript">

</script>
</body>
</html>