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
        <#assign key_list = news?keys/>
        <#assign value_list = news?values/>
        <#list key_list as key>
            <#assign seq_index = key_list?seq_index_of(key) />
            <#assign key_value = value_list[seq_index]/>
            title: ${key_value.title} <br>
            content : ${key_value.content} <br>
            <img src="/img/${key_value.nameImg}" alt="" width="300" height="300"><br>
        </#list>
    </body>
</html>