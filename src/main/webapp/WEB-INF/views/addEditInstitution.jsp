<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 12.10.19
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="edit.institution-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="login-page">
        <h2><spring:message code="edit.institution-title"/></h2>

        <form:form method="post" modelAttribute="institution">
            <div class="form-group">
                <spring:message code="edit.institution-name" var="namePlaceholder"/>
                <h3>${namePlaceholder}</h3>
                <form:input path="name" placeholder="${namePlaceholder}"/>
                <h3><form:errors path="name"/></h3>
            </div>
            <div class="form-group">
                <spring:message code="edit.institution-description" var="descriptionPlaceholder"/>
                <h3>${descriptionPlaceholder}</h3>
                <form:input path="description" placeholder="${descriptionPlaceholder}"/>
                <h3><form:errors path="description"/></h3>
            </div>

            <div class="form-group form-group--buttons">
                <button class="btn" type="submit"><spring:message code="edit.institution-save"/></button>
            </div>
        </form:form>
    </section>
</header>


<c:import url="footer.jsp"/>

</body>
</html>

