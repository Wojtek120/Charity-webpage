<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 06.10.19
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="logging.page-title"/></title>
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
    <h2><spring:message key="logging.page-sign.in"/></h2>
    <form method="post" action="<c:url value="/login"/> ">
        <div class="form-group">
            <input type="email" id="username" name="username" placeholder="<spring:message key="logging.page-email"/>" />
            <a href="<c:url value="/registration/resend"/>" class="btn btn--small btn--without-border reset-password"><spring:message key="logging.page-forgot.resend.email"/></a>

        </div>

        <div class="form-group">
            <input type="password" id="password" name="password" placeholder="<spring:message key="logging.page-password"/>" />
            <a href="<c:url value="/password/reset"/> " class="btn btn--small btn--without-border reset-password"><spring:message key="logging.page-forgot.password"/></a>

            <c:if test="${not empty param.error}">
                <h3><spring:message key="logging.page-error.message"/></h3>
            </c:if>
        </div>



        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/registration"/>" class="btn btn--without-border"><spring:message key="logging.page-sign.up"/></a>
            <button class="btn" type="submit"><spring:message key="logging.page-sign.in"/></button>
        </div>
    </form>
</section>

<c:import url="footer.jsp"/>
</body>
</html>
