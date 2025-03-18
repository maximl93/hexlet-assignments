package exercise;

// BEGIN
import java.util.Random;

public class ListThread extends Thread{

    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        Random random = new Random();

        try {
            for (int i = 0; i < 1000; i++) {
                Thread.sleep(1);
                list.add(random.nextInt(0, 250));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
// END
