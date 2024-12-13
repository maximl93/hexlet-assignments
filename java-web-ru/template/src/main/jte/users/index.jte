@import exercise.dto.users.UsersPage
@param UsersPage users

<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>${users.getHeader()}</h1>
    @for(var user : users.getUsers())
        <div>
            <h2><a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a></h2>
        </div>
    @endfor
</body>
</html>