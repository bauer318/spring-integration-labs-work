package ru.rsreu.kibamba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import ru.rsreu.kibamba.config.BasicIntegrationConfig;

import java.util.Scanner;

//@SpringBootApplication
@Configuration
public class SpringIntegrationApplication {

    public static void main(String[] args) {

        //SpringApplication.run(SpringIntegrationApplication.class, args);
        AbstractApplicationContext context
                = new AnnotationConfigApplicationContext(BasicIntegrationConfig.class);
        context.registerShutdownHook();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter q and press <enter> to exit the program: ");

        while (true) {
            String input = scanner.nextLine();
            if("q".equals(input.trim())) {
                break;
            }
        }
        System.exit(0);
    }

}
