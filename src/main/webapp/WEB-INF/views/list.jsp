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
	<div>
		<select name="type">						<!-- jsp의 name과 Criteria의 병수명이 일치하면 알아서 정보를 수집한다. -->
	                                <option value="" <c:out value="${pageMaker.cri.type==null?'selected':''}" />>--</option>
	                                <option value="T" <c:out value="${pageMaker.cri.type=='T'?'selected':''}" />>제목</option>
	                                <option value="C" <c:out value="${pageMaker.cri.type=='C'?'selected':''}" />>내용</option>
	                                <option value="W" <c:out value="${pageMaker.cri.type=='W'?'selected':''}" />>작성자</option>
	                                <option value="TC" <c:out value="${pageMaker.cri.type=='TC'?'selected':''}" />>제목+내용</option>
	                            </select>
	</div>
	<c:forEach var="list" items="${list}">  
		<table border=1>
<%-- 	    	<tr><td><input type="hidden" name="bno" value="${list.bno}" id="bno"></td></tr> --%>
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