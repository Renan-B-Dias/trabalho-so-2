import cpu.MyCpu;
import cpu.ThreadCpu;
import process.MyProcess;
import process.ThreadProcess;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class Main {
    public static void main(String[] args) {

        if(args.length < 6) {
            System.out.println("Quantidade de argumentos faltando.");
            System.exit(1);
        }

        int qtdProc = Integer.parseInt(args[0]);    // Quantidade de processos.
        int interSecs = Integer.parseInt(args[1]);
        int timeUnit = Integer.parseInt(args[2]);
        int timeQuantum = Integer.parseInt(args[3]);

        MyCpu cpu = new MyCpu(qtdProc);
        Thread processes[] = new Thread[qtdProc];

        ThreadProcess.interSeconds = interSecs;
        ThreadCpu.timeQuantum = timeQuantum;

        int f = 0;
        int argsIndex = 4;
        do {

            int id = Integer.parseInt(args[argsIndex++]);
            int prio = Integer.parseInt(args[argsIndex++]);

            processes[f++] = new Thread(new ThreadProcess(id, prio, timeUnit, cpu));

        } while(argsIndex < args.length);   // Or f > qtdProc

        System.out.println(new Date() + " Início da simulação");

        Thread cpuThread = new Thread(new ThreadCpu(cpu, qtdProc));     // Thread da Cpu
        cpuThread.setName("CPU THREAD");
        cpuThread.start();

        for(int i = 0; i < processes.length; i++)
            processes[i].setName("Process number " + i);
        for(Thread x: processes)
            x.start();
    }
}
