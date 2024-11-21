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

        <form action="/section/api/set/${section.id}" method="post" enctype="multipart/form-data">
            title:<input type="text" name="title" value="${section.title}">
            content:<input type="text" name="content" value="${section.content}"><br>
            teacher:<input type="text" name="teacher" value="${section.teacher.mail}"><br>
            <img src="/img/${section.nameImg}" alt=""><br>
            <input type="file" name="file">
            <input type="submit">
        </form>
    
    </body>
</html>