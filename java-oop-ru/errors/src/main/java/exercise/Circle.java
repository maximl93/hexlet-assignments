package exercise;

// BEGIN
public class Circle {
    private int radius;
    private Point center;

    Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }

        return Math.PI * radius * radius;
    }
}
// END
