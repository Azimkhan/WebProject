<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="m" uri="http://azimkhan.net/taglib" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="message"/>
<c:set var="user" scope="page" value="${m:user(pageContext.request)}"/>
<html>
<head>
    <title><fmt:message key="update_tour.title"/></title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<%@include file="../../WEB-INF/jspf/header.jspf"%>
<jsp:useBean id="dao" class="kz.enu.epam.azimkhan.tour.dao.TourDAO"/>


<div class="content">
    <h1><fmt:message key="update_tour.title"/></h1>

    <%@include file="_form.jspf"%>

</div>

<%@include file="../../WEB-INF/jspf/footer.jspf"%>
</body>
</html>