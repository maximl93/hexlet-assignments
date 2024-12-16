@template.layout.page(content = @`
    <form method="post" action="/users">
        <div>
            First name:
            <label>
                <input type="text" name="firstName">
            </label>
        </div>
        <div>
            Last name:
            <label>
                <input type="text" name="lastName">
            </label>
        </div>
        <div>
            Email:
            <label>
                <input type="email" required name="email">
            </label>
        </div>
        <div>
            Password:
            <label>
                <input type="password" required name="password">
            </label>
        </div>
        <input type="submit" value="Register">
    </form>
`)