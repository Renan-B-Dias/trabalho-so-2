/**
 * Created by yellow-umbrella on 02/06/17.
 */
public class MyProcess {

    private static int idCount = 0;

    public int id;
    public int runTime = 50;

    public MyProcess() {
        this.id = idCount++;
    }

}
