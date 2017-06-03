import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class MyCpu {

//    private LinkedList<MyProcess> FIFOQueue = new LinkedList<>();
    private PriorityQueue<MyProcess> FIFOQueue;

    public Semaphore empty;
    public Semaphore full;

    public MyCpu(int processQuantity) {
        empty = new Semaphore(processQuantity);
        full = new Semaphore(0);
        FIFOQueue = new PriorityQueue<MyProcess>();
    }

    public void insertProcess(MyProcess process) {
        try {
            empty.acquire();
        } catch(Exception e) {

        }

        FIFOQueue.add(process);

        try {
            full.release();
        } catch (Exception e) { }
    }

    public MyProcess removeProcess() {
        try {
            full.acquire();
        } catch(Exception e) {}

        MyProcess ret = FIFOQueue.remove();

        empty.release();
        return ret;
    }

//    public boolean queueIsEmpry() {
//        return FIFOQueue.isEmpty();
//    }

}
