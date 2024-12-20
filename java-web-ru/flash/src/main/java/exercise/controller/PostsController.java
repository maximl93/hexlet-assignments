package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    // BEGIN
    public static void create(Context context) {
        try {
            String name = context.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название поста должно быть не короче двух символов")
                    .get();
            String body = context.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Длина поста должна быть не короче 10 символов")
                    .get();

            Post newPost = new Post(name, body);
            PostRepository.save(newPost);
            context.sessionAttribute("flash", "Post was successfully created!");
            context.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            String name = context.formParam("name");
            String body = context.formParam("body");
            BuildPostPage buildPostPage = new BuildPostPage(name, body, e.getErrors());
            context.render("posts/build.jte", model("page", buildPostPage));
        }
    }

    public static void index(Context context) {
        PostsPage postsPage = new PostsPage(PostRepository.getEntities());
        postsPage.setFlash(context.consumeSessionAttribute("flash"));
        context.render("posts/index.jte", model("page", postsPage));
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }
}
