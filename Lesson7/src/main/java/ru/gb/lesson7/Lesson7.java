package ru.gb.lesson7;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lesson7 {

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource(
                "jdbc:h2:file:~/DATABASE", "databaseUser", "databasePassword").load();
        flyway.migrate();
        SpringApplication.run(Lesson7.class, args);
    }

}
