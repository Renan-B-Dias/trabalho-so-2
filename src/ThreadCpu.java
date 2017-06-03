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

//        cpu.removeProcess();
//        System.out.println(new Date() + " Process removed");

        int i = 4;

        while(i-- > 0) {
            running = cpu.removeProcess();

            int thisTimeQuantum = timeQuantum;

            while(thisTimeQuantum > 0) {

                try {
                    Thread.sleep(1000);
                } catch(Exception e) {}

//                System.out.println("Executou por um segundo");
                System.out.printf("Processo com id: %d exec por um segundo cputime:[%d]\n", running.id, running.cpuTime);

                running.cpuTime--;
                thisTimeQuantum -= 1000;
            }

            if(running.cpuTime < 0) {
                // Process ended...
                running = null;
                i--;
            } else {
                cpu.insertProcess(running);
            }
        }

    }
}
