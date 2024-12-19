package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void save(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = Security.encrypt(ctx.formParam("password"));
        String token = Security.generateToken();

        User newUser = new User(firstName, lastName, email, password, token);

        UserRepository.save(newUser);

        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(newUser.getId()));
    }

    public static void show(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        String token = ctx.cookie("token");

        User registeredUser = UserRepository.find(id).orElseThrow(() -> new NotFoundResponse("User not found"));
        String userToken = registeredUser.getToken();

        if (userToken.equals(token)) {
            UserPage userPage = new UserPage(registeredUser);
            ctx.render("users/show.jte", model("page", userPage));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
