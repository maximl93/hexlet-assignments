@import exercise.dto.users.UserPage
@param UserPage user

<html>
<head>
    <title>User</title>
</head>
<body>
    <div>
        <h2>${user.getUser().getFirstName()} ${user.getUser().getLastName()}</h2>
    </div>
</body>
</html>