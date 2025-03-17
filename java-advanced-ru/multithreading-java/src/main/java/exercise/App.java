package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        LOGGER.setLevel(Level.INFO);
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        maxThread.start();
        minThread.start();
        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Map.of("min", minThread.getMinNumber(), "max", maxThread.getMaxNumber());
    }
    // END
}
