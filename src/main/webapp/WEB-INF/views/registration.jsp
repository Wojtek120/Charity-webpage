<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 05.10.19
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="registration-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="login-page">
        <h2><spring:message key="registration-make.account"/></h2>
        <form:form method="post" modelAttribute="newUser">
            <div class="form-group">
                <spring:message key="registration-email" var="emailPlaceholder"/>
                <form:input path="email" type="email" placeholder="${emailPlaceholder}"/>
                <h3><form:errors path="email"/></h3>

                <c:if test="${not empty userExist}">
                    <h3><spring:message key="edit.password-error.user.exists"/></h3>
                </c:if>
            </div>
            <div class="form-group">
                <spring:message key="registration-first.name" var="firstNamePlaceholder"/>
                <form:input path="firstName" placeholder="${firstNamePlaceholder}"/>
                <h3><form:errors path="firstName"/></h3>
            </div>
            <div class="form-group">
                <spring:message key="registration-last.name" var="lastNamePlaceholder"/>
                <form:input path="lastName" placeholder="${lastNamePlaceholder}"/>
                <h3><form:errors path="lastName"/></h3>
            </div>
            <div class="form-group">
                <spring:message key="registration-phone.number" var="phoneNumberPlaceholder"/>
                <form:input path="phoneNumber" placeholder="${phoneNumberPlaceholder}"/>
                <h3><form:errors path="phoneNumber"/></h3>
            </div>
            <div class="form-group">
                <spring:message key="registration-password" var="passwordPlaceholder"/>
                <form:input path="password" type="password" name="password" placeholder="${passwordPlaceholder}"/>
                <h3><form:errors path="password"/></h3>
            </div>
            <div class="form-group">
                <spring:message key="registration-repeat.password" var="repeatPasswordPlaceholder"/>
                <form:input path="repeatedPassword" type="password" name="password2"
                            placeholder="${repeatPasswordPlaceholder}"/>
                <h3><form:errors path="repeatedPassword"/></h3>

                <c:if test="${not empty wrongPass}">
                    <h3><spring:message key="edit.password-error.message"/></h3>
                </c:if>
            </div>

            <div class="form-group form-group--buttons">

                <a href="<c:url value="/login"/>" class="btn btn--without-border"><spring:message key="registration-log.in"/> </a>
                <button class="btn" type="submit"><spring:message key="registration-create.account"/></button>
            </div>
        </form:form>
    </section>
</header>


<c:import url="footer.jsp"/>

</body>
</html>
