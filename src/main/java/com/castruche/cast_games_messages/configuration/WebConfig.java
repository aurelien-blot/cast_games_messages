package com.castruche.cast_games_messages.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //TODO VERIF CONFIG SECURITE
        registry.addMapping("/api/**")
                .allowedMethods("GET")
                .allowedHeaders("*")
            .allowedOrigins("http://localhost:5173");
    }

}
