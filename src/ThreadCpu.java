import java.util.Date;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class ThreadCpu implements Runnable {

    private MyCpu cpu;
    public MyProcess running;

    public static int timeQuantum;

    public ThreadCpu(MyCpu cpu) {
        this.cpu = cpu;
    }

    @Override
    public void run() {

        int i = 4;

        while(i > 0) {
            running = cpu.removeProcess();

            int thisTimeQuantum = timeQuantum;

            while(thisTimeQuantum > 0) {

                try {
                    Thread.sleep(100);
                } catch(Exception e) {}

                running.cpuTime -= 100;
                thisTimeQuantum -= 100;

                System.out.printf("Processo com id: %d exec por um segundo cputime:[%d]\n", running.id, running.cpuTime);

                if(running.cpuTime <= 0)
                    break;
            }

            if(running.cpuTime <= 0) {
                // Process ended...
                running = null;
                i--;
            } else
                cpu.insertProcess(running);
        }

    }
}
