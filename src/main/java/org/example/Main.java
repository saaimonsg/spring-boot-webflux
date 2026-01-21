package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;

import java.util.TimeZone;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //TODO        Convertir a zona local solo en la UI
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication app = new SpringApplication(Main.class);

        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
    }
}