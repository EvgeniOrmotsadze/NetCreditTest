package com.evgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by root_pc on 2/6/2016.
 */



@SpringBootApplication
public class Application extends SpringBootServletInitializer {
/*

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(com.evgo.Application.class);
    }
*/


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}