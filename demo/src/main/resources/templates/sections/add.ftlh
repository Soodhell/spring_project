<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>add</title>
    </head>
    <body>

        <form action="/section/api/add" method="post" enctype="multipart/form-data">
            title:   <input type="text" name="title"><br>
            content: <input type="text" name="content"><br>
            teacher: <input type="text" name="teacher"><br>
            photo:   <input type="file" name="file"><br>
            <input type="submit">
        </form>

    </body>
</html>