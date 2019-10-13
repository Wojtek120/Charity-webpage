<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 13.10.19
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="authentication.info-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>

    <section class="help" id="foundations">
        <h2><spring:message code="authentication.info-title"/></h2>
        <h3 style="font-size: 2rem"><spring:message code="authentication.info-info"/></h3>
    </section>
</header>


<c:import url="footer.jsp"/>

</body>
</html>
