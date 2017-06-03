/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class Main {
    public static void main(String[] args) {

        MyCpu cpu = new MyCpu();

        Thread thread = new Thread(new ThreadCpu(cpu));
        thread.setName("CPU THREAD");
        thread.start();

        cpu.insertProcess(new MyProcess());
    }
}
