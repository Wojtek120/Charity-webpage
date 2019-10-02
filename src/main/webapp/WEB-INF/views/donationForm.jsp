<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 02.10.19
  Time: 00:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="donation.form-donations"/></title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <c:import url="header.jsp"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="donation.form-give.unwanted.things" /><br/>
                <span class="uppercase"><spring:message code="donation.form-in.need" /></span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title"><spring:message code="donation.form-only.4.steps" /></div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em><spring:message code="donation.form-step.1.count"/></em><span><spring:message code="donation.form-step.1.message"/> </span></div>
                    </li>
                    <li>
                        <div><em><spring:message code="donation.form-step.2.count"/></em><span><spring:message code="donation.form-step.2.message"/></span></div>
                    </li>
                    <li>
                        <div><em><spring:message code="donation.form-step.3.count"/></em><span><spring:message code="donation.form-step.3.message"/></span></div>
                    </li>
                    <li>
                        <div><em><spring:message code="donation.form-step.4.count"/></em><span><spring:message code="donation.form-step.4.message"/></span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3><spring:message code="donation.form-important"/></h3>
            <p data-step="1" class="active">
                <spring:message code="donation.form-important.message.1"/>
            </p>
            <p data-step="2">
                <spring:message code="donation.form-important.message.2"/>
            </p>
            <p data-step="3">
                <spring:message code="donation.form-important.message.3"/>
            </p>
            <p data-step="4"><spring:message code="donation.form-important.message.4"/></p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <spr:form method="post" modelAttribute="donationDto" id="donationForm">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3><spring:message code="donation.form-select.info"/></h3>


                <c:forEach items="${categories}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" value="${category.id}"/>
                            <span class="checkbox"><spr:checkbox path="categories" value="${category.id}"/></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step"><spring:message code="donation.form-next"/></button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3><spring:message code="donation.form-give.number.of.bags"/> </h3>

                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="donation.form-number.of.bags"/>
                        <spr:input path="quantity" type="number" name="bags" step="1" min="1"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="donation.form-back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="donation.form-next"/></button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3><spring:message code="donation.form-select.institution"/> </h3>

                <c:forEach items="${institutions}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <spr:radiobutton path="institution" value="${institution.id}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">
                          ${institution.description}
                  </div>
                </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="donation.form-back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="donation.form-next"/></button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3><spring:message code="donation.form-give.adress"/></h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4><spring:message code="donation.form-adress"/></h4>
                        <div class="form-group form-group--inline">
                            <label> <spring:message code="donation.form-street"/> <spr:input path="street" type="text" name="address"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="donation.form-city"/> <spr:input path="city" type="text" name="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="donation.form-zip.code"/> <spr:input path="zipCode" type="text" name="postcode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="donation.form-phone.number"/> <spr:input path="phoneNumber" type="phone" name="phone"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4><spring:message code="donation.form-pick.up.date"/></h4>
                        <div class="form-group form-group--inline">
                            <label> <spring:message code="donation.form-date"/> <spr:input path="pickUpDate" type="date" name="data"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> <spring:message code="donation.form-time"/> <spr:input path="pickUpTime" type="time" name="time"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="donation.form-pick.up.comment"/>
                                <spr:textarea path="pickUpComment" name="more_info" rows="5"></spr:textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="donation.form-back"/></button>
                    <button type="button" class="btn next-step"><spring:message code="donation.form-next"/></button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3><spring:message code="donation.form-summary"/></h3>

                <div class="summary">
                    <div class="form-section">
                        <h4><spring:message code="donation.form-you.are.giving"/></h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text" id="bagsSummary"><spring:message code="donation.form-bags"/></span>
<%--                                <span class="summary--text">4 worki ubrań w dobrym stanie dla dzieci</span>--%>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text"
                                >Dla fundacji "Mam marzenie" w Warszawie</span
                                >
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4><spring:message code="donation.form-adress"/> </h4>
                            <ul>
                                <li>Prosta 51</li>
                                <li>Warszawa</li>
                                <li>99-098</li>
                                <li>123 456 789</li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4><spring:message code="donation.form-pick.up.date"/> </h4>
                            <ul>
                                <li>13/12/2018</li>
                                <li>15:40</li>
                                <li>Brak uwag</li>
                            </ul>
                        </div>
                    </div>
                </div>

<%--                <spr:errors path="*"/>--%>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step"><spring:message code="donation.form-back"/></button>
                    <button type="submit" class="btn"><spring:message code="donation.form-accept"/></button>
                </div>
            </div>
        </spr:form>
    </div>
</section>

<c:import url="footer.jsp"/>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
