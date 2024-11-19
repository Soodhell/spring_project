<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Редактирование записи</title>
    </head>
    <body>

        <form action="/news/api/set/${new.id}" method="post" enctype="multipart/form-data">
            title:<input type="text" name="title" value="${new.title}">
            content:<input type="text" name="content" value="${new.content}"><br>
            <img src="/img/${new.nameImg}" alt=""><br>
            <input type="file" name="file">
            <input type="submit">
        </form>
    
    </body>
</html>