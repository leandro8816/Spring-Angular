package com.event.demo;

import com.event.demo.UI.ConsoleInterface;
import com.event.demo.auth.AuthenticationService;
import com.event.demo.evento.EventService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class  DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);


		AuthenticationService authService = context.getBean(AuthenticationService.class);
		EventService eventService = context.getBean(EventService.class);
		ConsoleInterface consoleInterface = new ConsoleInterface(authService, eventService);
		consoleInterface.start();


	}




}
