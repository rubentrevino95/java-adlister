<%--
  Created by IntelliJ IDEA.
  User: rubentrevino
  Date: 4/22/20
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Product show page"/>
    </jsp:include>
    <title>Title</title>
</head>
<body>
    <jsp:include page="../partials/navbar.jsp">
        <div class="container">
<%--            Title of product    --%>
            <h1>Current Product: ${product.title}</h1>

<%--            Price of product    --%>
            <p>Price: $${product.priceInCents/100}</p>

<%--            Description of product  --%>
            <p><${product.description}/p>
        </div>

    </jsp:include>
</body>
</html>
