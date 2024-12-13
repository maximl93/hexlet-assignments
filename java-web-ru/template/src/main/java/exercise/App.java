package exercise;

import io.javalin.Javalin;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", context -> {
            String header = "All users";
            UsersPage users = new UsersPage(USERS, header);
            context.render("users/index.jte", model("users", users));
        });

        app.get("/users/{id}", context -> {
            Long id = context.pathParamAsClass("id", Long.class).get();
            User foundUser = USERS.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElse(null);

            UserPage user = new UserPage(foundUser);

            if (foundUser == null) {
                context.status(404);
                context.result("User not found");
            } else {
                context.render("users/show.jte", model("user", user));
            }
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
