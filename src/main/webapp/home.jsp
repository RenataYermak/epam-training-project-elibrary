<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
        }

        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #fc6; /* Цвет текста */
        }

        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }

        #header { /* Верхний блок */
            background: #0080c0; /* Цвет фона */
            padding: 10px; /* Поля вокруг текста */
        }

        #sidebar { /* Левая колонка */
            float: left; /* Обтекание справа */
            border: 1px solid rgba(51, 51, 51, 0.4); /* Параметры рамки вокруг */
            border-radius: 5px;
            background-color: rgba(211, 211, 211, 0.09);
            width: 20%; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
            margin: 10px 10px 20px 5px; /* Значения отступов */
        }

        #content { /* Правая колонка */
            margin: 10px 5px 20px 22%; /* Значения отступов */
            padding: 5px; /* Поля вокруг текста */
            border: 1px solid rgba(51, 51, 51, 0.4); /* Параметры рамки вокруг */
            border-radius: 5px;
            background-color: rgba(211, 211, 211, 0.09);
        }

        #footer { /* Нижний блок */
            background: #333; /* Цвет фона */
            padding: 5px; /* Поля вокруг текста */
            color: #fff; /* Цвет текста */
            clear: left; /* Отменяем действие float */
        }
    </style>
</head>
<body>
<div id="header">
    <h1>eLibrary</h1>

    <form action="controller">
        <button style="float: right" type="submit" name="command" value="sign_out">Log Out</button>
    </form>
    <div style="float: right" >
        Hello, <a href="user.jsp?id=${authUser.id}">${authUser.login}</a>
    </div>
</div>
<div id="sidebar">
    <p><a href="b_all.html">Users</a></p>
    <p><a href="b_author.html">All Books</a></p>
    <p><a href="b_author.html">My Booked Books</a></p>
    <p><a href="b_author.html">My Ordered Books</a></p>
</div>
<div id="content">
    <h2>Books</h2>
    <table  class="table">
        <tr class="row header">
            <th class="cell">Title</th>
            <th class="cell">Author</th>
            <th class="cell">Category</th>
            <th class="cell">Date</th>
            <th class="cell">OverallRating</th>
            <th class="cell">Number</th>
            <th class="cell"></th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr class="row">
                <td class="cell">${book.title}</td>
                <td class="cell">${book.author}</td>
                <td class="cell">${book.category.name}</td>
                <td class="cell">${book.date}</td>
                <td class="cell">${book.overallRating}</td>
                <td class="cell">${book.number}</td>
                <td class="cell"><a href="book.jsp?id=${book.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="footer">&copy; Yermak Renata</div>
</body>
</html>
