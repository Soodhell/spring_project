<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Аккаунт</title>
    </head>
    <body>

        <#if user??>
            ${user.mail}
            ${user.firstName}
            ${user.lastName}
        <#else>
            Error
        </#if>

        <br>

        <#if sections??>
            <#list sections as section>
                ${section.section.title}
                <img src="/img/${section.section.nameImg}" width="300" height="300">
            </#list>
        </#if>

    </body>
</html>