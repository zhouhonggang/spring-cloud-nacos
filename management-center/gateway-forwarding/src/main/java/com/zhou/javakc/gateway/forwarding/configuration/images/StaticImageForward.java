package com.zhou.javakc.gateway.forwarding.configuration.images;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.MalformedURLException;

@Configuration
public class StaticImageForward {

    @Value("${images.path}")
    private String imagesPath;

    @Bean
    RouterFunction<ServerResponse> staticResourceRouter() throws MalformedURLException {
        System.out.println("imagesPath:"+imagesPath);
        return RouterFunctions.resources("/images/**", new FileUrlResource(imagesPath));
    }

}
