package com.socurites.jive.example.konal.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class KonalWebApplication {
	public static void main(String[] args) {
        SpringApplication.run(KonalWebApplication.class, args);
    }
}
