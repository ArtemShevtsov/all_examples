import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by Artem_Shevtsov on 5/17/2016.
 */
public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<DemonThreadExample> fTask = new FutureTask<>(new Callable<DemonThreadExample>() {
            @Override
            public DemonThreadExample call() throws Exception {
                DemonThreadExample d = new DemonThreadExample();
                d.setValue(158);
                Thread.sleep(2000);
                System.out.println("from task");
                return d;
            }
        });

        Thread thread = new Thread(fTask);
        thread.start();
        Thread.sleep(1000);
        fTask.cancel(false);
        System.out.println("is cancelled: " + fTask.isCancelled());
        if(!fTask.isCancelled()) {
            System.out.println("Task Execution Result is " + fTask.get());
        }
    }

}
