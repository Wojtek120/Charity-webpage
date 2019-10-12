<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 12.10.19
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="delete.confirmation-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="help" id="foundations">
        <h2><spring:message key="delete.confirmation-title"/> ${nameOfItemToDelete}?</h2>
        <h3>${nameOfItemToDelete} <spring:message key="delete.confirmation-will.be.deleted.with.all.dependencies"/></h3>


        <form:form method="post" action="${requestScope['javax.servlet.forward.query_string']}">
            <div class="form-group form-group--buttons">
                <a onclick="history.back()" class="btn btn--without-border"><spring:message
                        key="delete.confirmation-back"/></a>
                <button class="btn" type="submit"><spring:message key="delete.confirmation-confirm"/></button>
            </div>
        </form:form>

    </section>

</header>


<c:import url="footer.jsp"/>

</body>
</html>
