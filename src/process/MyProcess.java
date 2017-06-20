package process;

/**
 * Created by yellow-umbrella on 02/06/17.
 * Classe que define um processo com id, estado, prioridade e tempo de execução.
 */
public class MyProcess implements Comparable<MyProcess> {

    /**
     * Enumerador que define todos os estados de um processo.
     */
    public enum State { pronto, executando, terminado }

    /**
     * Id unico do processo.
     */
    public int id;

    /**
     * Prioridade do processo.
     */
    public int priority;

    /**
     * Tempo de necessário de cpu para processo finalizar.
     */
    public int cpuTime;

    /**
     * Estado atual do processo (Pronto, executado, terminado)
     */
    public State state;

    /**
     * Construtor que recebe id, prioridade e constante de unidade.
     * @param id id unico do processo.
     * @param priority prioridade do processo.
     * @param timeUnit constante para calcular tempo de processamento.
     */
    public MyProcess(int id, int priority, int timeUnit) {
        this.id = id;
        this.priority = priority;
        this.state = null;
        this.cpuTime = (2 * (id+1) * timeUnit);
    }

    /**
     * Implementação da Interface do Java Comparable
     * @param process Processo a ser comparado.
     * @return
     */
    @Override
    public int compareTo(MyProcess process) {
        if(this.priority > process.priority)
            return -1;
        else if(this.priority < process.priority)
            return 1;
        return 0;
    }
}
