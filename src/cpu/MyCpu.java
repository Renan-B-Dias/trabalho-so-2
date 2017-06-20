package cpu;

import process.MyProcess;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

/**
 * Created by yellow-umbrella on 02/06/17.
 * Classe que define uma Cpu com uma fila de prontos(Por prioridade).
 */
public class MyCpu {

    /**
    * Fila de processos em pronto que se comporta como uma FIFO de prioridades.
    */
    private PriorityQueue<MyProcess> FIFOQueue;

    /**
     * Semáforo que indica se pode ser adicionado processos na fila de prontos.
     */
    public Semaphore empty;

    /**
     * Semáforo que indica se pode retirar algum processo da fila de prontos para execuçaõ.
     */
    public Semaphore full;

    /**
     * Constutor padrão que recebe a quantidade de processos a serem executados.
     * @param processQuantity
     */
    public MyCpu(int processQuantity) {
        empty = new Semaphore(processQuantity);
        full = new Semaphore(0);
        FIFOQueue = new PriorityQueue<MyProcess>();
    }

    /**
     * Método que inseri um processo na fila de prontos da CPU
     * @param process
     */
    public void insertProcess(MyProcess process) {
        try {
            empty.acquire();
        } catch(Exception e) {

        }

        FIFOQueue.add(process);

        try {
            full.release();
        } catch (Exception e) { }
    }

    /**
     * Método que retira um processo da fila de prontos (No caso o com maior prioridade) e retorna processo.
     * @return
     */
    public MyProcess removeProcess() {
        try {
            full.acquire();
        } catch(Exception e) {}

        MyProcess ret = FIFOQueue.remove();

        empty.release();
        return ret;
    }

    /**
     * Método que retorna a prioridade do próximo processo a ser executado.
     * @return
     */
    public int prioNext() {
        try {
            return FIFOQueue.peek().priority;
        } catch(Exception e) {
            return 0;
        }
    }
}
