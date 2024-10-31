package exercise;

// BEGIN
public class App {
    public static void printSquare(Circle circle) {
        try {
            System.out.println(Math.round(circle.getSquare()));
        } catch (NegativeRadiusException exc) {
            System.out.println(exc);
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
