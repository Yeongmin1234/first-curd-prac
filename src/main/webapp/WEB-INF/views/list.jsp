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
</head>
<body>
	<c:forEach var="list" items="${list}">  
		<table border=1>
	    	<input type="hidden" name="bno" value=${list.bno} id="bno">
			<tr><td>제목</td><td><a href="/read?bno=${list.bno}">${list.title}</a></td></tr>
			<tr><td>img</td><td><div class="uploadResult">
					<div>
						<a href='/read?bno=${list.bno}'>
							<img src='${list.t}'>
						</a>
					</div>
			</div></td>
			</tr>
		</table>
			
	</c:forEach>
</body>
</html>