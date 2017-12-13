package com.example.kalah;

import com.example.kalah.model.KalahBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KalahApplication {

	public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(KalahApplication.class, args);

//        start the bux bot runner
        ((KalahBoard) ctx.getBean("kalahBoardImpl")).generate(6);
	}
}
