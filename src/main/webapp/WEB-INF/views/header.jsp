<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 01.10.19
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<nav class="container container--70">
    <ul class="nav--actions">
        <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="<c:url value="/registration"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>

    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
        <li><a href="<c:url value="/#steps"/>" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="<c:url value="/#about"/>" class="btn btn--without-border">O nas</a></li>
        <li><a href="<c:url value="/#foundations"/>" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="<c:url value="/#contact"/>" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
</body>
</html>
