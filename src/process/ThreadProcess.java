package process;

import cpu.MyCpu;

/**
 * Created by yellow-umbrella on 11/06/17.
 */
public class ThreadProcess implements Runnable {

	public static int interSeconds;

	private MyProcess process;
	private MyCpu cpu;

	public ThreadProcess(int id, int priority, int timeUnit, MyCpu cpu) {
		this.process = new MyProcess(id, priority, timeUnit);
		this.cpu = cpu;
	}

	public void run() {
		try {
			Thread.sleep(this.process.id * interSeconds);
		} catch(Exception e) {}

		System.out.println("A thread " + this.process.id + " chegou a fila de prontos");
		cpu.insertProcess(this.process);
	}

}