package exercise;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> apartments, int n) {
        return apartments.stream()
                .sorted(Home::compareTo)
                .limit(n)
                .map(Objects::toString)
                .collect(Collectors.toList());
    }
}
// END
