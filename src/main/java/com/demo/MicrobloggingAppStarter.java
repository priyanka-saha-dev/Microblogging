package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MicrobloggingAppStarter implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MicrobloggingAppStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("MicrobloggingAppStarter#run()");
    }
}