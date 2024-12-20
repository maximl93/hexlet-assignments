package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context context) {
        MainPage mainPage = new MainPage(context.sessionAttribute("loggedUser"));
        context.render("index.jte", model("page", mainPage));
    }

    public static void build(Context context) {

        LoginPage loginPage = new LoginPage(null, null);
        context.render("build.jte", model("page", loginPage));
    }

    public static void login(Context context) {
        String name = context.formParam("name");
        String password = context.formParam("password");
        String encryptedPassword = encrypt(password);

        if (UsersRepository.existsByName(name)
                && UsersRepository.findByName(name).get().getPassword().equals(encryptedPassword)) {
            context.sessionAttribute("nickname", null);
            context.sessionAttribute("error", null);

            context.sessionAttribute("loggedUser", name);
            MainPage mainPage = new MainPage(context.sessionAttribute("loggedUser"));
            context.render("index.jte", model("page", mainPage));
            context.redirect(NamedRoutes.rootPath());
            return;
        }

        LoginPage loginPage = new LoginPage(name, "Wrong username or password");
        context.render("build.jte", model("page", loginPage));

    }

    public static void logout(Context context) {
        context.sessionAttribute("loggedUser", null);
        context.redirect(NamedRoutes.rootPath());
    }
    // END
}
