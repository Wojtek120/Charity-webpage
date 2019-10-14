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
    <title><spring:message code="admin.institutions.profile-title"/></title>

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
        <h2><spring:message code="admin.institutions.profile-title"/></h2>


        <!-- SLIDE 1 -->
        <div class="help--slides active">

            <h3 style="font-size: 2rem"><a class="myButton" href="<c:url value="/admin/institutions/add"/>"><spring:message
                    code="admin.institutions.profile-add.new"/></a></h3>

            <table id="table" class="display" style="width:100%">
                <thead>
                <tr>
                    <th>
                        <spring:message code="admin.institutions.no"/>
                    </th>
                    <th>
                        <spring:message code="admin.institutions.name"/>
                    </th>
                    <th>
                        <spring:message code="admin.institutions.description"/>
                    </th>
                    <th>
                        <spring:message code="admin.institutions.edit"/>
                    </th>
                    <th>
                        <spring:message code="admin.institutions.delete"/>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${institutions}" var="institution" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${institution.name}</td>
                        <td>${institution.description}</td>
                        <c:url value=""/>
                        <td><a class="myButton" href="<c:url value="/admin/institutions/edit/${institution.id}"/>"><spring:message
                                code="admin.institutions.edit"/></a></td>
                        <td><a class="myButton" href="<c:url value="/admin/institutions/delete/${institution.id}"/>"><spring:message
                                code="admin.institutions.delete"/></a></td>
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
