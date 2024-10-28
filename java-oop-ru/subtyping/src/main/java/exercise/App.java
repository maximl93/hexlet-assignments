package exercise;

import java.util.Map;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> temp = storage.toMap();

        temp.entrySet().forEach(entry -> {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        });
    }
}
// END
