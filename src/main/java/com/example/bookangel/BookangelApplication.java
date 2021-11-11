package com.example.bookangel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class BookangelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookangelApplication.class, args);
    }
}
