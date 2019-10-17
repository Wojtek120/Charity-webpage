<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 16.10.19
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="resend.token-title"/></title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <c:import url="header.jsp"/>
</header>

<section class="login-page">
    <h2><spring:message code="resend.token-title"/></h2>
    <form:form method="post" modelAttribute="email">
        <div class="form-group">
            <spring:message code="resend.token-email" var="email"/>
            <form:input path="email" placeholder="${email}"/>
            <h3><form:errors path="email"/></h3>

            <c:if test="${not empty message}">
                <h3>${message}</h3>
            </c:if>


        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit"><spring:message code="resend.token-send"/></button>
        </div>
    </form:form>
</section>

<c:import url="footer.jsp"/>
</body>
</html>

