@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page


@template.layout.page(content = @`
    @if(page.getName() == null)
        <div class="md-3">
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        </div>
    @else
        <form class="row g-3" action="${NamedRoutes.logoutPath()}" method="post">
            <div class="col-auto">
                <label for="staticEmail2" class="visually-hidden"></label>
                <input type="text" readonly class="form-control-plaintext" id="staticEmail2" value="${page.getName().toString()}">
            </div>
            <div class="col-auto">
                <input type="submit" class="btn btn-primary mb-3" value="Выйти"/>
            </div>
        </form>
    @endif


`)