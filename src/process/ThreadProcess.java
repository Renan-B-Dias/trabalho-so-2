package process;

import cpu.MyCpu;

import java.util.Date;

/**
 * Created by yellow-umbrella on 11/06/17.
 * Classe que define uma Thread que possui um Processo a ser adicionado na fila de prontos.
 */
public class ThreadProcess implements Runnable {

	/**
	 * Constante para calcular tempo de 'geração' do processo.
	 */
	public static int interSeconds;

	/**
	 * Processo que será inserido na fila de prontos da CPU.
	 */
	private MyProcess process;

	/**
	 * Instancia de CPU compartilada entre as Threads.
	 */
	private MyCpu cpu;

	/**
	 * Constutor que recebe o id, priority, timeUnit e instancia compartilhada da CPU.
	 * @param id id unico.
	 * @param priority prioridade do processo.
	 * @param timeUnit constante para calcular tempo de execução.
	 * @param cpu instância compartilhada da CPU.
	 */
	public ThreadProcess(int id, int priority, int timeUnit, MyCpu cpu) {
		this.process = new MyProcess(id, priority, timeUnit);
		this.cpu = cpu;
	}

	/**
	 * Método run() que começa a execução da Thread.
	 * Processo é 'gerado' após "process.id * interSeconds" tempo que é o momento em que o processo
	 * tenta a entrar na cpu.
	 * Depois que esse tempo acaba esse processo é inserido na fila de prontos.
	 */
	public void run() {
		try {
			Thread.sleep(this.process.id * interSeconds);
		} catch(Exception e) {}

		this.process.state = MyProcess.State.pronto;
		System.out.println(new Date() + " A thread " + this.process.id + " chegou a fila de prontos [" +
				this.process.id + ": " + this.process.state + ", " + this.process.priority + ", " +
				this.process.cpuTime + "]");
		cpu.insertProcess(this.process);
	}

}