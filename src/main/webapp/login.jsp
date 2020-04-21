<%--
  Created by IntelliJ IDEA.
  User: rubentrevino
  Date: 4/21/20
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Login Form</h3>
<form action="login.jsp" method="POST">
    Username:<input type="text" id="username" name="username" /><br><br>
    Password:<input type="password" id="password" name="password"><br><br>
</form>

<%
    String redirectURL = "http://localhost:8080/profile.jsp";
    if (request.getParameter("password").equals("passoword") && request.getParameter("username").equals("admin")) {
        response.sendRedirect(redirectURL);
    }
%>

</body>
</html>
