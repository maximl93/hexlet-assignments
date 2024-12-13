@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(
    content = @`
        @for(User user : page.getUsers())
            <div>
                <h1>${user.getFirstName()} ${user.getLastName()}</h1>
                <p>${user.getEmail()}</p>
            </div>
        @endfor
    `
)
