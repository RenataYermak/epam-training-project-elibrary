<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${authUser.login}</title>
</head>
<body>
<div>
    Hello, <a href="user.jsp?id=${authUser.id}">${authUser.login}</a>
</div>
<form action="registration.jsp">
    <button type="submit">Registration</button>
</form>
<form action="controller">
    <button type="submit" name="command" value="sign_out">Sign Out</button>
</form>
<br/>
<form action="controller" method="post">
    Login: <input type="text" name="login" value="${authUser.login}">
    <br/>
    Password: <input type="password" name="password" value="${authUser.login}">
    <br/>
    Role:
    <label>
        <select name="userRole">
            <option disabled>Choose Role:</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select>
    </label>
    <br/>
    <input type="text" hidden name="userId" value="${authUser.id}">
    <button type="submit" name="command" value="edit_user">Submit changes</button>
    <button type="submit" name="command" value="find_user">Cancel</button>
</form>
</body>
</html>
