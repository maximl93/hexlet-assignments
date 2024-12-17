package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {return "/posts";}

    public static String onePostPath(Long id) {return onePostPath(String.valueOf(id));}

    public static String onePostPath(String id) {return "/posts/" + id;}
    // END
}
