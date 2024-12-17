@import exercise.dto.posts.PostsPage
@param PostsPage page


@template.layout.page(content = @`
    @for(var post : page.getPosts())
        <div>
            <a href="/posts/${post.getId()}">${post.getName()}</a>
        </div>
    @endfor
    @if(page.getPage() - 1 != 0)
        <a href="/posts?page=${page.getPage() - 1}">Previous</a>
    @endif
    <a href="/posts?page=${page.getPage() + 1}">Next</a>
`)