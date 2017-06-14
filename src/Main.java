import cpu.MyCpu;
import cpu.ThreadCpu;
import process.MyProcess;
import process.ThreadProcess;

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

        // Debug
//        if(true) {
//            int processId1 = 0;
//            int prio1 = 50;
//
//            int processId2 = 1;
//            int prio2 = 52;
//
//            int processId3 = 2;
//            int prio3 = 54;
//
//            int processId4 = 3;
//            int prio4 = 57;
//
//            int timeUnit = 100;
//            int timeQuantum = 500;
//
//            ThreadCpu.timeQuantum = timeQuantum;
//
//            ThreadProcess.interSeconds = 300;
//
//            processes = new Thread[] {
//                    new Thread(new ThreadProcess(processId1, prio1, timeUnit, cpu)),
//                    new Thread(new ThreadProcess(processId2, prio2, timeUnit, cpu)),
//                    new Thread(new ThreadProcess(processId3, prio3, timeUnit, cpu)),
//                    new Thread(new ThreadProcess(processId4, prio4, timeUnit, cpu))
//            };
//        }

        System.out.println("Início da simulação");

        Thread cpuThread = new Thread(new ThreadCpu(cpu, qtdProc));     // Thread da Cpu
        cpuThread.setName("CPU THREAD");
        cpuThread.start();

        for(int i = 0; i < processes.length; i++)
            processes[i].setName("Process number " + i);
        for(Thread x: processes)
            x.start();
    }
}
