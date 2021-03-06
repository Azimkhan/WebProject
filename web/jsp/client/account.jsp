<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="auth" uri="http://azimkhan.net/taglib/auth" %>
<%@taglib prefix="n" uri="http://azimkhan.net/taglib/notification" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${auth:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="account.title"/></title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>


<div class="content">
    <h1><fmt:message key="account.title"/> </h1>
    <p><fmt:message key="account.info"/>:</p>
    <c:choose>
        
        <c:when test="${not empty orders and orders.size() > 0}">
            <c:set var="total" value="0"/>
            <table class="order-table">
                <thead>
                <tr>
                    <th><fmt:message key="tour_table.tourname"/></th>
                    <th><fmt:message key="account.table.date"/></th>
                    <th><fmt:message key="account.table.amout"/></th>
                    <th><fmt:message key="account.table.status"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <c:set var="total" value="${total + order.amount}"/>
                    <tr>
                        <td>${order.tour.tourname}</td>
                        <td><fmt:formatDate value="${order.dateTime}" pattern="d.M.Y H:m"/> </td>
                        <td><fmt:formatNumber maxFractionDigits="1" type="number"  groupingUsed="false" value="${order.amount}"/> USD</td>
                        <td><c:choose>
                            <c:when test="${order.paid}">
                                <fmt:message key="account.paid"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="account.payment_wait"/>
                            </c:otherwise>
                        </c:choose></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <p><b><fmt:message key="account.table.total"/>: <fmt:formatNumber maxFractionDigits="1" type="number"  groupingUsed="false" value="${total}"/> USD</b></p>
        </c:when>
        <c:otherwise>
            <p><fmt:message key="account.no_orders"/></p>
        </c:otherwise>
    </c:choose>

</div>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>