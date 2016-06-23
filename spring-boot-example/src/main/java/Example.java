import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by artem_shevtsov on 16.06.16.
 */
@RestController
@EnableAutoConfiguration
public class Example {
    @RequestMapping("/")
    String name(){
        return "it works";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }
}
