package passion.spring.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class EnvironmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnvironmentApplication.class, args);
    }

}
