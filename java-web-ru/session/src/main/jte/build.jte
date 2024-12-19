@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    @if(page.getError() != null)
        <div>
            <h3>${page.getError()}</h3>
        </div>
    @endif


    <form class="row g-3" action="${NamedRoutes.loginPath()}" method="post">
        <div class="col-auto">
            <label for="formLoginInput" class="visually-hidden">Name:</label>
                @if(page.getName() != null)
                <input type="text" class="form-control" id="formLoginInput" name="name" value="${page.getName()}"/>
                @else
                <input type="text" class="form-control" id="formLoginInput" name="name" placeholder="Name">
                @endif
        </div>
        <div class="col-auto">
            <label for="formPasswordInput" class="visually-hidden">Password:</label>
                <input type="password" class="form-control" id="formPasswordInput" name="password" placeholder="Password"/>
        </div>
        <div class="col-auto">
            <input type="submit" class="btn btn-primary mb-3" value="Войти"/>
        </div>
    </form>
`)
