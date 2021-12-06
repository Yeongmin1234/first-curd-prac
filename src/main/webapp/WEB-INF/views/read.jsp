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
<script src='//cdnjs.cloudflare.com/ajax/libs/jquery-throttle-debounce/1.1/jquery.ba-throttle-debounce.min.js'></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/read.js"></script>
</head>
<body>
	<table border=1>
    	<tr><input type="hidden" value="${read.bno}" name="bno" id="bno"></tr>
    	<tr><td>제목</td><td>${read.title}</td></tr>
	    <tr><td>내용</td><td>${read.text}</td></tr>
    	<tr><td>작성자</td><td>${read.writer}</td></tr>
		<tr><td>작성일자</td><td>${read.date}</td></tr>
	</table>
	<form action="create" method="post">
		<button><a href="/update?bno=${read.bno}">수정하기</a></button>
		<button><a href="/delete?bno=${read.bno}">삭제하기</a></button>  
	</form>
	<div class="uploadResult">
		<ul></ul>
	</div>
</body>
</html>