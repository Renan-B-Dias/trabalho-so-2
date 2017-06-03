import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class MyCpu {

    private LinkedList<MyProcess> FIFOQueue = new LinkedList<>();

    private Semaphore empty = new Semaphore(1);
    private Semaphore full = new Semaphore(0);


    public void insertProcess(MyProcess process) {
        try {
            empty.acquire();
        } catch(Exception e) {

        }
        FIFOQueue.addFirst(process);
        try {
            full.release();
        } catch (Exception e) { }
    }

    public MyProcess removeProcess() {
        try {
            full.acquire();
        } catch(Exception e) {}

        empty.release();
        return FIFOQueue.removeLast();
    }

    public boolean queueIsEmpry() {
        return FIFOQueue.isEmpty();
    }

}
