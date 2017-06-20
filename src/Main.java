import cpu.MyCpu;
import cpu.ThreadCpu;
import process.ThreadProcess;

import java.util.Date;

/**
 * Created by yellow-umbrella on 02/06/17.
 * Classe Main que inicia a observação.
 */
public class Main {
    public static void main(String[] args) {

        /**
         * Caso não haja argumentos suficiente uma mensagem de erro é mostrada para o usuário.
         */
        if(args.length < 6) {
            System.out.println("Quantidade de argumentos faltando.");
            System.exit(1);
        }

        /**
         * Pega a quantidade de processo a ser executado. ("num")
         */
        int qtdProc = Integer.parseInt(args[0]);    // Quantidade de processos.

        /**
         * Constante para calcular tempo de 'geração' do processo.
         */
        int interSecs = Integer.parseInt(args[1]);

        /**
         * Constante para calcular tempo de processamento na CPU.
         */
        int timeUnit = Integer.parseInt(args[2]);

        /**
         * Time quantum da CPU.
         */
        int timeQuantum = Integer.parseInt(args[3]);

        /**
         * Instância compartilhada de CPU.
         */
        MyCpu cpu = new MyCpu(qtdProc);

        /**
         * Array de Threads que recebera todas as Threads de todos os processos.
         */
        Thread processes[] = new Thread[qtdProc];

        ThreadProcess.interSeconds = interSecs;
        ThreadCpu.timeQuantum = timeQuantum;

        /**
         * Do-While que instancia todos os processos a serem colocados na CPU.
         */
        int f = 0;
        int argsIndex = 4;
        do {
            int id = Integer.parseInt(args[argsIndex++]);
            int prio = Integer.parseInt(args[argsIndex++]);

            processes[f++] = new Thread(new ThreadProcess(id, prio, timeUnit, cpu));
        } while(argsIndex < args.length);   // Or f > qtdProc

        System.out.println(new Date() + " Início da simulação");

        /**
         * Thread da CPU que vai executar os Processos.
         * Starr da Thread da CPU.
         */
        Thread cpuThread = new Thread(new ThreadCpu(cpu, qtdProc));     // Thread da Cpu
        cpuThread.setName("CPU THREAD");    // Debug
        cpuThread.start();

        /**
         * Nomeação das Threads de acordo com o id do processo e start das Threads.
         */
        for(int i = 0; i < processes.length; i++)
            processes[i].setName("Process number " + i);
        for(Thread x: processes)
            x.start();
    }
}
