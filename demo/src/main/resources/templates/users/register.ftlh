<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Регистрация</title>
    </head>
    <body>

    <form action="/api/register" method="post">
        <input type="text" name="mail" placeholder="mail">
        <input type="password" name="password" placeholder="password">
        <input type="text" name="first_name" placeholder="first name">
        <input type="text" name="last_name" placeholder="last name">
        <input type="submit">
    </form>
    
    </body>
</html>