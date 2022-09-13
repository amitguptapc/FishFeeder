package com.feeder.fishfeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FishFeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishFeederApplication.class, args);
    }

}
