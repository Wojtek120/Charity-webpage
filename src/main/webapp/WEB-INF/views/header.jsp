<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <sec:authorize access="isAuthenticated()">
        <sec:authentication var="user" property="principal" />

        <ul class="nav--actions">
            <li class="logged-user">
                <spring:message key="header-hello"/> ${user.username}
                <ul class="dropdown">
                    <li><a href="#"><spring:message key="header-profil"/></a></li>
                    <li><a href="#"><spring:message key="header-settings"/></a></li>
                    <li><a href="#"><spring:message key="header-donations"/></a></li>
                    <li><a href="javascript:{}" onclick="document.getElementById('logout').submit(); return false;"><spring:message key="header-log.out"/></a></li>
                </ul>
            </li>
        </ul>

        <form action="<c:url value="/logout"/>" method="post" id="logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border"><spring:message key="header-sign.in"/></a></li>
            <li><a href="<c:url value="/registration"/>" class="btn btn--small btn--highlighted"><spring:message key="header-sign.up"/></a></li>
        </ul>
    </sec:authorize>
    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active"><spring:message key="header-start"/></a></li>
        <li><a href="<c:url value="/#steps"/>" class="btn btn--without-border"><spring:message key="header-info"/></a></li>
        <li><a href="<c:url value="/#about"/>" class="btn btn--without-border"><spring:message key="header-about"/></a></li>
        <li><a href="<c:url value="/#foundations"/>" class="btn btn--without-border"><spring:message key="header-foundations"/></a></li>
        <li><a href="<c:url value="/#contact"/>" class="btn btn--without-border"><spring:message key="header-contact"/></a></li>
    </ul>
</nav>
</body>
</html>
