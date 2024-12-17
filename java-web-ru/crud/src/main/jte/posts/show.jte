@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(content = @`
    <div>
        <h1>${page.getPost().getName()}</h1>
    </div>
    <div>
        <p>${page.getPost().getBody()}</p>
    </div>
`)

