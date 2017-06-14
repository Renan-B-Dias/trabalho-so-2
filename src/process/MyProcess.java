package process;

/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class MyProcess implements Comparable<MyProcess> {

    public int id;
    public int priority;
    public int cpuTime;

    public MyProcess(int id, int priority, int timeUnit) {
        this.id = id;
        this.priority = priority;

        this.cpuTime = (2 * (id+1) * timeUnit);
    }

    @Override
    public int compareTo(MyProcess process) {
        if(this.priority > process.priority)
            return -1;
        else if(this.priority < process.priority)
            return 1;
        return 0;
    }
}
