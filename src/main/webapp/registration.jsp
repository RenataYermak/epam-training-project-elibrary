
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User registration</title>
</head>
<body>
<div>
    Hello, <a href="user.jsp?id=${authUser.id}">${authUser.login}</a>
</div>
<br/>
<form name="loginForm" method="post" action="controller">
    <div style="display: block;margin-left: auto;margin-right: auto ">
        <h1>User registration</h1>
        <label>
            <input type="text" name="login" placeholder="login">
        </label>
        <br/>
        <label>
            <input type="password" name="password" placeholder="password">
        </label>
        <label>
            <select name="userRole">
                <option value="ADMIN">Admin</option>
                <option value="USER">User</option>
            </select>
        </label>
        <br/>
        <button type="submit" name="command" value="registration">Add User</button>
    </div>
</form>
</body>
</html>
