@import exercise.util.NamedRoutes
@import exercise.model.Post
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
    @for(Post post : page.getPosts())
        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
    @endfor
    `)