package learn.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Artem_Shevtsov on 2/1/2016.
 */
public class App {
    private Client client;
    private EventLogger logger;

    public App(Client client, EventLogger logger) {
        this.client = client;
        this.logger = logger;
    }

    private void logEvent(String message){
        String msg = message.replaceAll(Integer.toString(client.getId()), client.getFullName());
        logger.logEvent(msg);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App application = ctx.getBean(App.class);
        application.logEvent("Some event for 1");
        application.logEvent("Some event for 1 and 2");
    }
}
