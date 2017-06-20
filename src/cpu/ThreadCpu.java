package cpu;

import process.MyProcess;

import java.util.Date;

/**
 * Created by yellow-umbrella on 02/06/17.
 * Classe que define uma Thread que possui uma CPU e executa os processos saidos da fila de prontos.
 */
public class ThreadCpu implements Runnable {

    /**
     * Instância compartilhada da CPU.
     */
    private MyCpu cpu;

    /**
     * Processo que esta rodado na Thread atualmente.
     */
    public MyProcess running;

    /**
     * Quantidade de processos a serem executados nessa Thread.
     */
    private int processAmount;

    /**
     * Tempo de execução na CPU 'cedido' a cada processo.
     */
    public static int timeQuantum;

    /**
     * Construtor que recebe a instância compartilhada da CPU e a quantidade de processos a serem executados.
     * @param cpu
     * @param processAmount
     */
    public ThreadCpu(MyCpu cpu, int processAmount) {
        this.cpu = cpu;
        this.processAmount = processAmount;
        this.running = null;
    }

    /**
     * Método que começa a Thread. Nesse método um processo é retirado da fila de Prontos da CPU
     * e colocado em execução onde a cada iteração sua prioridade diminui assim como o time quantum
     * e o tempo necessário para o termino desse processo.
     * A cada iteração é visto se a prioridade do próximo processo a ser retirado da fila de Prontos
     * é maior ou igual a prioridade do processo que estava executando e teve ser time quantum esgotado.
     * Caso sim esse processo é retirado do CPU e colocado de volta na fila de Prontos onde onde o processo
     * que tiver a maior prioridade, em ordem FIFO, será retirado para execução. Caso essa condição não seja
     * cumprida e o processo que teve seu time quantum esgotado continuar sendo o processo com maior prioridade
     * esse processo pode continuar executando com um novo time quantum.
     */
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
            } else if (running.cpuTime > 0) {
                if(cpu.prioNext() >= running.priority) {
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
