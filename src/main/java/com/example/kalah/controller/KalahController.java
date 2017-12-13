package com.example.kalah.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KalahController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

    @RequestMapping("/")
    public String kalah(Map<String, Object> model) {
        model.put("message", this.message);
        return "kalah";
    }

    @RequestMapping("/play")
    public String kalah(Map<String, Object> model) {
        model.put("message", this.message);
        return "kalah";
    }
}