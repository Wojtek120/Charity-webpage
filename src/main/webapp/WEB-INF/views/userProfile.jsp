<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 07.10.19
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="user.profile-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="help" id="foundations">
        <h2><spring:message key="user.profile-title"/></h2>

        <!-- SLIDE 1 -->
        <div class="help--slides active">
            <p><spring:message key="user.profile-thanks.giving.message"/></p>

            <ul class="help--slides-items">

                <li>
                    <div class="profile">
                        <div class="title">${user.firstAndLastName}</div>
                        <div class="subtitle"><spring:message key="user.profile-first.and.last.name"/></div>
                    </div>
                </li>
                <li>
                    <div class="profile">
                        <div class="title">${user.email}</div>
                        <div class="subtitle"><spring:message key="user.profile-email"/></div>
                    </div>
                </li>
                <li>
                    <div class="profile">
                        <div class="title">${user.phoneNumber}</div>
                        <div class="subtitle"><spring:message key="user.profile-phone.number"/></div>
                    </div>
                </li>

            </ul>
        </div>

        <form action="<c:url value="/user/edit/password"/>">
            <div class="form-group form-group--buttons">
                <a href="<c:url value="/user/edit/profile"/>" class="btn btn--without-border"><spring:message
                        key="user.profile-edit.profile"/></a>
                <button class="btn" type="submit"><spring:message key="user.profile-edit.password"/></button>
            </div>
        </form>

    </section>

</header>


<c:import url="footer.jsp"/>

</body>
</html>
