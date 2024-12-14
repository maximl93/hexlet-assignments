@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage page

@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}">
        <input type="submit" value="Search">
    </form>


    @if(!page.getUsers().isEmpty())
        @for(User user : page.getUsers())
            <div>
                <h1>${user.getFirstName()} ${user.getLastName()}</h1>
                <h2>${user.getEmail()}</h2>
            </div>
        @endfor
    @endif
`)