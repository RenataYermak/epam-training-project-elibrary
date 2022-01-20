<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<div style="display: block;margin-left: auto;margin-right: auto; margin-top: 200px; width: 300px">
    <form name="loginForm" method="post" action="controller">
        <div style="display: block;margin-left: auto;margin-right: auto ">
            <h2>Welcome to eLibrary</h2>
            <label>
                <input type="text" name="login" placeholder="login">
            </label>
            <br/>
            <label>
                <input type="password" name="password" placeholder="password">
            </label>
            <br/>
            <button type="submit" name="command" value="sign_in">Sign In</button>
        </div>
    </form>
</div>
</body>
</html>
