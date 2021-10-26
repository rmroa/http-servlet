<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp"%>
    <h1>Manufacturer:</h1>
    <ul>
        <c:forEach var="manufacturer" items="${requestScope.manufacturers}">
            <li>
                <a href=${pageContext.request.contextPath}/models?manufacturerId=${manufacturer.id}>${manufacturer.description}</a>
            </li>
        </c:forEach>
</ul>
</body>
</html>