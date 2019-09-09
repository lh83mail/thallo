<html>
<head>
<link rel="stylesheet" href="css/wro.css"/>
</head>
<body>
    Hello, Welcome!

    <form method="post" action="/logout">
        <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button  type="submit">Log Out</button>
    </form>
</body>
</html>