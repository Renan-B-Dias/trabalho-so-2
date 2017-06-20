package cpu;

import process.MyProcess;

import java.util.Date;

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
        this.running = null;
    }

    @Override
    public void run() {

        int i = processAmount;

        while(i > 0) {

            if(running == null) {
                running = cpu.removeProcess();
                running.state = MyProcess.State.executando;
                System.out.println(new Date() + " A thread " +
                        running.id + " está executando [" + running.id + ": " +
                        running.state + ", " + running.priority + ", " + running.cpuTime + "]");
            }

            int thisTimeQuantum = timeQuantum;

            while(thisTimeQuantum > 0) {

                try {
                    Thread.sleep(100);
                } catch(Exception e) {}

                running.cpuTime -= 100;
                thisTimeQuantum -= 100;
                running.priority--;

                if(running.cpuTime <= 0)
                    break;
            }

            if(running.cpuTime <= 0) {
                // Process ended...
                running.state = MyProcess.State.terminado;
                System.out.println(new Date() + " A thread " + running.id + " esgotou seu tempo total de processamento [" + running.id + ": " +
                                running.state + ", " + running.priority + ", " + running.cpuTime + "]");
                running = null;
                i--;
            } else if (running.cpuTime >= 0) {
                if(cpu.prioNext() > running.priority) {
                    running.state = MyProcess.State.pronto;
                    cpu.insertProcess(running);
                    System.out.println(new Date() + " A thread " + running.id + " voltou à fila de prontos [" + running.id + ": " +
                            running.state + ", " + running.priority + ", " + running.cpuTime + "]");
                    running = null;
                }
            }
        }

        System.out.println(new Date() + " Término da simulação");
    }
}
