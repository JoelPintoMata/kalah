package com.example.kalah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.example.kalah.model.player",
        "com.example.kalah.model.board",
        "com.example.kalah.strategy"})
public class KalahApplication {

	public static void main(String[] args) {
        SpringApplication.run(KalahApplication.class, args);
	}
}
