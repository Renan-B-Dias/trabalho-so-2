import java.util.Date;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class ThreadCpu implements Runnable {

    private MyCpu cpu;

    public ThreadCpu(MyCpu cpu) {
        this.cpu = cpu;
    }

    @Override
    public void run() {

        cpu.removeProcess();
        System.out.println(new Date() + " Process removed");

    }
}
