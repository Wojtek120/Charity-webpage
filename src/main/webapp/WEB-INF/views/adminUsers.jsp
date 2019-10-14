<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 12.10.19
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="admin.users-title"/></title>

    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>

    <script src="<c:url value="/resources/js/table.js"/>"></script>

</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>


    <section class="help">
        <h2><spring:message code="admin.users-title"/></h2>


        <!-- SLIDE 1 -->
        <div class="help--slides active">

            <table id="table" class="display" style="width:100%">
                <thead>
                <tr>
                    <th>
                        <spring:message code="admin.users-no"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-name"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-email"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-phone.number"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-active"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-edit"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-block"/>
                    </th>
                    <th>
                        <spring:message code="admin.users-delete"/>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${users}" var="user" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${user.firstAndLastName}</td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td><input type="checkbox" disabled="disabled" checked="${user.enabled}"></td>
                        <td><a class="myButton" href="<c:url value="/admin/users/edit/${user.id}"/>"><spring:message
                                code="admin.users-edit"/></a></td>
                        <td><a class="myButton" href="<c:url value="/admin/users/block/${user.id}"/>"><spring:message
                                code="admin.users-block"/></a></td>
                        <td><a class="myButton" href="<c:url value="/admin/users/delete/${user.id}"/>"><spring:message
                                code="admin.users-delete"/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>


    </section>

</header>


<c:import url="footer.jsp"/>

</body>
</html>
