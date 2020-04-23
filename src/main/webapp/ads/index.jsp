<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rubentrevino
  Date: 4/23/20
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="View our ads"/>
    </jsp:include>
</head>
<body>
    <c:forEach var="" items="${allTheAds}">
        <h1><c:out value="ad.title" /></h1>
        <p><c:out value="ad.description" /></p>
    </c:forEach>
</body>
</html>
