import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Artem_Shevtsov on 5/20/2016.
 * Also see CyclicBarrier and Phaser
 */
public class CountdownLatchExample {
    public static class Worker implements Runnable{
        private CountDownLatch start;

        public Worker(CountDownLatch start) {
            this.start = start;
        }

        @Override
        public void run() {
            try{
                System.out.println("Thread - " + Thread.currentThread().getName() + " waiting");
                start.countDown();
//                System.out.println("Thread - " + Thread.currentThread().getName() + " count is - " + start.getCount());
                start.await();
                System.out.println("Thread - " + Thread.currentThread().getName() + " started");
            } catch (InterruptedException e){
                System.out.println("Thread - " + Thread.currentThread().getName() + " was interrupted");
            }
        }
    }


    public static void main(String[] args) {
        CountDownLatch start = new CountDownLatch(3);
        new Thread(new Worker(start), "Thread_1").start();
        new Thread(new Worker(start), "Thread_2").start();
        new Thread(new Worker(start), "Thread_3").start();
    }
}
