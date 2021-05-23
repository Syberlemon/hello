package wh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author froid
 * Date:  2021/5/17
 */
@RestController
@SpringBootApplication
public class ServiceApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @RequestMapping("/hello")
    public String helloWorld() {
        return "hello, world.";
    }
}
