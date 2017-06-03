import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class MyCpu {

    private LinkedList<MyProcess> FIFOQueue = new LinkedList<>();

    public Semaphore empty;
    public Semaphore full;

    public MyCpu(int processQuantity) {
        empty = new Semaphore(processQuantity);
        full = new Semaphore(0);
    }

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

        MyProcess ret = FIFOQueue.removeLast();

        empty.release();
        return ret;
    }

//    public boolean queueIsEmpry() {
//        return FIFOQueue.isEmpty();
//    }

}
