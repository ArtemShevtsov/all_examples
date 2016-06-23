import com.sun.org.apache.bcel.internal.generic.INEG;

/**
 * Created by Artem_Shevtsov on 5/16/2016.
 */
public class DemonThreadExample {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DemonThreadExample{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Thread demonThread = new Thread(){
            @Override
            public void run() {
                try{
                    int i = 0;
                    while (true){
                        System.out.println(++i + "\t-\tDemon Thread is runnong");
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e){
                    System.out.println("Demon Thread was interrupted");
                }
            }
        };

        Thread simpleThread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        demonThread.setDaemon(true);
        demonThread.start();
        simpleThread.start();
        Thread.sleep(2000);
    }
}
