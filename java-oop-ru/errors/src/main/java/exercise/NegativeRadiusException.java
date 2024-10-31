package exercise;

// BEGIN
public class NegativeRadiusException extends Exception {
    private String message;

    NegativeRadiusException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
// END
