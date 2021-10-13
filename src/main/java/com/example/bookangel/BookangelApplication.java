package com.example.bookangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BookangelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookangelApplication.class, args);
    }

}
