<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="edit.password-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<section class="login-page">
    <h2><spring:message key="edit.password-title"/></h2>
    <form:form method="post" modelAttribute="passwordDto">
        <div class="form-group">
            <spring:message key="edit.password-password" var="password"/>
            <h3>${password}</h3>
            <form:input path="password" type="password" placeholder="${password}"/>
            <h3><form:errors path="password"/></h3>
        </div>
        <div class="form-group">
            <spring:message key="edit.password-repeat.password" var="repeatPassword"/>
            <h3>${repeatPassword}</h3>
            <form:input path="repeatedPassword" type="password" placeholder="${repeatPassword}"/>
            <h3><form:errors path="repeatedPassword"/></h3>

            <c:if test="${not empty param.error}">
                <h3><spring:message key="edit.password-error.message"/></h3>
            </c:if>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit"><spring:message key="edit.password-save"/></button>
        </div>
    </form:form>
</section>


<c:import url="footer.jsp"/>

</body>
</html>