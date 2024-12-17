package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void post(Context context) {
        Long id = context.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        PostPage page = new PostPage(post);
        context.render("posts/show.jte", model("page", page));
    }

    public static void allPosts(Context context) {
        var page = context.queryParamAsClass("page", Integer.class).getOrDefault(1);

        PostsPage postsPage = new PostsPage(PostRepository.findAll(page, 5), page);

        context.render("posts/index.jte", model("page", postsPage));
    }
    // END
}
