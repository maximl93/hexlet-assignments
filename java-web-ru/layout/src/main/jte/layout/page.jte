@import gg.jte.Content
@param Content content

<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
    <header>
        <a href="/">Main page</a>
        <a href="/users">All users</a>
    </header>
    <div>
        ${content}
    </div>
    <div class="footer">
        <p>This footer for every page</p>
        <a href="https://github.com/maximl93">My GitHub</a>
    </div>
</body>
</html>