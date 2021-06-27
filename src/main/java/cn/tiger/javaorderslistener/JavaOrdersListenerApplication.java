package cn.tiger.javaorderslistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JavaOrdersListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaOrdersListenerApplication.class, args);
    }

}
