<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Новость</title>
    </head>
    <body>

        title: ${section.title}<br>
        content: ${section.content}<br>
        teacher: <a href="/get-user/${section.teacher.mail}">${section.teacher.mail}</a><br>
        <img src="/img/${section.nameImg}" alt="" width="300" height="300">

        <form action="/registration-section/add/${section.id}" method="post">
            <input type="submit">
        </form>

    </body>
</html>