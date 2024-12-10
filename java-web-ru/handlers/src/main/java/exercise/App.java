package exercise;

import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.bundledPlugins.enableDevLogging();
        });

        app.get("/phones", context -> context.json(Data.getPhones()));
        app.get("/domains", context -> context.json(Data.getDomains()));

        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
