package cz.bernhard.memsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
public class MemsourceInterviewApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(MemsourceInterviewApplication.class, args);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
}
