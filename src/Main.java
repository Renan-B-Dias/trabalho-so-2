import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class Main {
    public static void main(String[] args) {

        MyProcess processes[];

        if(true) {
            int processId1 = 0;
            int prio1 = 50;

            int processId2 = 1;
            int prio2 = 52;

            int processId3 = 2;
            int prio3 = 54;

            int processId4 = 3;
            int prio4 = 57;

            int timeUnit = 100;
            int timeQuantum = 500;

            ThreadCpu.timeQuantum = timeQuantum;

            processes = new MyProcess[4];
            processes[0] = new MyProcess(processId1, prio1, timeUnit);
            processes[1] = new MyProcess(processId2, prio2, timeUnit);
            processes[2] = new MyProcess(processId3, prio3, timeUnit);
            processes[3] = new MyProcess(processId4, prio4, timeUnit);
        }

        MyCpu cpu = new MyCpu(processes.length);

        Thread thread = new Thread(new ThreadCpu(cpu));     // Thread da Cpu
//        thread.setName("CPU THREAD");
        thread.start();


        cpu.insertProcess(processes[0]);
        cpu.insertProcess(processes[1]);
        cpu.insertProcess(processes[2]);
        cpu.insertProcess(processes[3]);
    }
}
