package com.example.websocketapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class WebsocketAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(MessageRepo repo){
        return args -> {
            for(int i=0; i < 20; i++) {
                Message message = new Message();
                message.setAuthor("author " + i);
                message.setMessage("message " + i);
                repo.save(message);
            }
        };
    }

}
