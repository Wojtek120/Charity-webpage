<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 12.10.19
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message key="admin.panel-title"/> </title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="help" id="foundations">
        <h2><spring:message key="admin.panel-title"/></h2>

        <!-- SLIDE 1 -->
        <div class="help--slides active">


            <ul class="help--slides-items">

                <li>
                    <div class="profile">
                        <div class="title" style="font-size: 3rem"><spring:message key="admin.panel-info.message"/></div>
                    </div>
                </li>

                <li>
                    <div class="profile">
                        <div class="title"><a href="<c:url value="/admin/institutions"/>"><spring:message key="admin.panel-institutions"/></a></div>
                    </div>
                </li>

                <li>
                    <div class="profile">
                        <div class="title"><a href="<c:url value="/admin/users"/>"><spring:message key="admin.panel-users"/></a></div>
                    </div>
                </li>

                <li>
                    <div class="profile">
                        <div class="title"><a href="<c:url value="/admin/admins"/>"><spring:message key="admin.panel-admins"/></a></div>
                    </div>
                </li>


            </ul>
        </div>
    </section>

</header>


<c:import url="footer.jsp"/>

</body>
</html>
