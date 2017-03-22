package com.example;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by antonsakhno on 21.03.17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class TemperatureServer {

    public static void main(String[] args) {
        SpringApplication.run(TemperatureServer.class, args);
    }

    @Bean
    public MongoClient mongoClient() {
        return new Fongo("db").getMongo();
    }
}
