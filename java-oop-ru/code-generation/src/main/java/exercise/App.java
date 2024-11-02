package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path filePath, Car car) throws IOException {
        Files.write(filePath, car.serialize().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static Car extract(Path pathFile) throws IOException {
        return Car.deserialize(Files.readString(pathFile));
    }
}
// END
