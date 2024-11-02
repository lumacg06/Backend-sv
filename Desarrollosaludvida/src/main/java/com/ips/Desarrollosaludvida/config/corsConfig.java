package com.ips.Desarrollosaludvida.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class corsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")// Escribir la url del proyecto front a conectar
                .allowedMethods("*")// PODEMOS ESPECIFICAR LOS METODOS A PERMITIR DESDE EL FRONT,GET,POST,ETC
                .allowedHeaders("*")// PERMITIR TODOS LOS HEADERS
                .allowCredentials(true)// PERMITIR LAS CREDENCIALES
                .maxAge(3600); // ESTABLECER EL TIEMPO DE CADUCIDAD EN SEGUNDOS
    }
}