<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">

    <c:import url="header.jsp"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="main-start.helping"/> <br/>
                <spring:message code="main-start.give.unwanted.things.in.goog.hands"/>
            </h1>
        </div>
    </div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${quantitySum}</em>

            <h3><spring:message code="main-given.bags"/></h3>
            <p><spring:message code="main-lorem1"/> </p>
        </div>

        <div class="stats--item">
            <em>${institutionsNumber}</em>
            <h3><spring:message code="main-donated.institutions"/></h3>
            <p><spring:message code="main-lorem2"/></p>
        </div>

    </div>
</section>

<section class="steps">
    <h2><spring:message code="main-only.4.steps"/></h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3><spring:message code="main-select.items"/></h3>
            <p><spring:message code="main-select.examples.of.items"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3><spring:message code="main-pack.it"/></h3>
            <p><spring:message code="main-use.bags"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3><spring:message code="main-decide.who.you.want.to.give.it"/></h3>
            <p><spring:message code="main-select.your.place"/></p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3><spring:message code="main-get.courier.out"/></h3>
            <p><spring:message code="main-courier.info"/></p>
        </div>
    </div>

    <a href="#" class="btn btn--large"><spring:message code="main-create.account"/></a>
</section>

<section class="about-us">
    <div class="about-us--text">
        <h2><spring:message code="main-about"/></h2>
        <p><spring:message code="main-lorem1"/></p>
        <img src="<c:url value="/resources/images/signature.svg"/>" class="about-us--text-signature" alt="Signature"/>
    </div>
    <div class="about-us--image"><img src="<c:url value="/resources/images/about-us.jpg"/>" alt="People in circle"/>
    </div>
</section>

<section class="help">
    <h2><spring:message code="main-who.do.we.help"/></h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p><spring:message code="main-institutions.info"/></p>

        <ul class="help--slides-items">


            <c:forEach items="${institutions}" var="institution" varStatus="loopStatus">
                <c:if test="${(loopStatus.index mod 2) == 0}">
                    <li>
                </c:if>

                <div class="col">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">${institution.description}</div>
                </div>

                <c:if test="${((loopStatus.index mod 2) == 1) || (loopStatus.last)}">
                    </li>
                </c:if>
            </c:forEach>

        </ul>
    </div>

</section>

<c:import url="footer.jsp"/>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
