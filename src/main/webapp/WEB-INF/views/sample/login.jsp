<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>Login with Username and Password</h3>
    <form action="/login" method="POST">
        <table>
            <tbody>
                <tr>
                    <td>User:</td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                	<td>
                		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
						    <font color="red">
						        <p>Your login attempt was not successful due to <br/>
						            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
						        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
						    </font>
						</c:if>
                	</td>
                </tr>
                <tr>
                    <td colspan="2"><input name="submit" type="submit" value="Login"></td>
	                <td><input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
