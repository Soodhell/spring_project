<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="/css/style.css" rel="stylesheet" >
        <title>Document</title>
    </head>
    <body>

    <#list news as new>
        ${new.title}
        ${new.content}
        <img src="/img/${new.nameImg}" width="300" height="300">
    </#list>


    </body>
</html>