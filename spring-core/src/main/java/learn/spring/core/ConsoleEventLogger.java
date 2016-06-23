package learn.spring.core;

/**
 * Created by Artem_Shevtsov on 2/1/2016.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(String msg) {
        System.out.println("[ConsoleLoggerMessage]: " + msg);
    }
}
