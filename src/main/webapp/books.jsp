<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
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
            position: absolute;
            left: 0;
            bottom: 0;
            width: max-content;
            height: 10px
        }

        .editButton {
            margin: 0;
            float: left;
            margin-right: 5px;
        }

        hr {
            border: none; /* Убираем границу */
            background-color: rgba(51, 51, 51, 0.4); /* Цвет линии */

            height: 1px; /* Толщина линии */
        }
    </style>
</head>
<body>
<div id="header">

    <h1>eLibrary</h1>

    <form action="controller">
        <button style="float: right" type="submit" name="command" value="sign_out">Log Out</button>
    </form>
    <div style="float: right">
        Hello, <a href="/controller?command=find_user&userId=${authUser.id}">${authUser.login}</a>
    </div>
</div>
<div id="sidebar">
    <p><i class='fas fa-user-friends'></i> <a href="/controller?command=find_users">Users</a></p>
    <p><i class='fas fa-book'></i>  <a href="/controller?command=find_books">All Books</a></p>
    <p><i class='fas fa-stream'></i> <a href="/controller?command=find_ordered_books&userId=${authUser.id}">My Ordered Books</a></p>
    <p><i class='fas fa-book-open'></i> <a href="/controller?command=find_booked_books&userId=${authUser.id}">My Reserved Books</a></p>
    <hr/>
    <p><i class='fas fa-globe'></i> <a href="b_author.html">About eLibrary</a></p>
</div>
<div id="content">
    <h2>Books</h2>
    <hr/>
    <table class="table">
        <tr class="row header">
            <th class="cell">Title</th>
            <th class="cell">Author</th>
            <th class="cell">Category</th>
            <th class="cell">Date</th>
            <th class="cell">OverallRating</th>
            <th class="cell">Number</th>
            <th class="cell">Action</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr class="row">
                <td class="cell"><a href="/controller?command=find_book&bookId=${book.id}">${book.title}</a></td>
                <td class="cell">${book.author}</td>
                <td class="cell">${book.category.name}</td>
                <td class="cell">${book.date}</td>
                <td class="cell">${book.overallRating}</td>
                <td class="cell">${book.number}</td>
                <td class="cell">
                        <%--<a href="/controller?command=editBook&bookId=${book.id}">edit</a>--%>
                    <form action="controller">

                        <button class="editButton">Edit</button>
                    </form>
                    <form action="controller">
                        <button class="editButton" type="submit" name="command" value="edit_book">Order</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer" id="footer">eLibrary&copy; 2022</div>
</body>
</html>
