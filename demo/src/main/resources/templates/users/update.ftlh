<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Редактирование профиля</title>
    </head>
    <body>

    <form action="/api/update/${mail}" method="post">
        first name: <input type="text" name="firstname" value="${first_name}"><br>
        last name:  <input type="text" name="lastname"  value="${last_name}" ><br>
        <input type="submit">
    </form>

    <br>
    Удалить пользователя: <br>
    <form action="/api/delete/${mail}" method="post">
        <input type="submit">
    </form>

    </body>
</html>