
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
<div>
    Hello, <a href="user.jsp?id=${authUser.id}">${authUser.login}</a>
</div>
<table class="center">
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Role</th>
        <th>Status</th>
        <th>Activation Date</th>
        <th>Deactivation Date</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.role.name}</td>
            <td>${user.status.name}</td>
            <td>${user.activationDate}</td>
            <td>${user.deactivationDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
