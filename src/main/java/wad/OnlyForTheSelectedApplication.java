package wad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlyForTheSelectedApplication {

    public static void main(String[] args) {
        System.out.println("STARTING, for travis");
        SpringApplication.run(OnlyForTheSelectedApplication.class, args);
    }
}
