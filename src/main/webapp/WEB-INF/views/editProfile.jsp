<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 08.10.19
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="edit.profile-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="login-page">
        <h2><spring:message key="edit.profile-title"/></h2>
        <form:form method="post" modelAttribute="user">
            <div class="form-group">
                <h3>Email</h3>
                <spring:message key="edit.profile-email" var="emailPlaceholder"/>
                <form:input path="email" type="email" placeholder="${emailPlaceholder}"/>
                <form:errors path="email"/>
            </div>
            <div class="form-group">
                <h3>Imię</h3>
                <spring:message key="edit.profile-first.name" var="firstNamePlaceholder"/>
                <form:input path="firstName" placeholder="${firstNamePlaceholder}"/>
                <form:errors path="firstName"/>
            </div>
            <div class="form-group">
                <h3>Nazwisko</h3>
                <spring:message key="edit.profile-last.name" var="lastNamePlaceholder"/>
                <form:input path="lastName" placeholder="${lastNamePlaceholder}"/>
                <form:errors path="lastName"/>
            </div>
            <div class="form-group">
                <h3>Numer telefonu</h3>
                <spring:message key="edit.profile-phone.number" var="phoneNumberPlaceholder"/>
                <form:input path="phoneNumber" placeholder="${phoneNumberPlaceholder}"/>
                <form:errors path="phoneNumber"/>
            </div>

            <div class="form-group form-group--buttons">
                <button class="btn" type="submit"><spring:message key="edit.profile-save"/></button>
            </div>
        </form:form>
    </section>
</header>


<c:import url="footer.jsp"/>

</body>
</html>

