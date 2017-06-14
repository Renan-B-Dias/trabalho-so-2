package cpu;

import process.MyProcess;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class ThreadCpu implements Runnable {

    private MyCpu cpu;
    public MyProcess running;
    private int processAmount;

    public static int timeQuantum;

    public ThreadCpu(MyCpu cpu, int processAmount) {
        this.cpu = cpu;
        this.processAmount = processAmount;
    }

    @Override
    public void run() {

        int i = processAmount;

        while(i > 0) {
            running = cpu.removeProcess();

            System.out.println("A thread " + running.id + " está executando");

            int thisTimeQuantum = timeQuantum;

            while(thisTimeQuantum > 0) {

                try {
                    Thread.sleep(100);
                } catch(Exception e) {}

                running.cpuTime -= 100;
                thisTimeQuantum -= 100;
                running.priority--;

//                System.out.printf("Processo com id: %d exec por um segundo cputime:[%d]\n", running.id, running.cpuTime);

                if(running.cpuTime <= 0)
                    break;
            }

            if(running.cpuTime <= 0) {
                // Process ended...
                System.out.println("A thread " + running.id + " esgotou seu tempo total de processamento");
                running = null;
                i--;
            } else {
                cpu.insertProcess(running);
                System.out.println("A thread " + running.id + " voltou à fila de prontos");
            }
        }

        System.out.println("Término da simulação");
    }
}
