package com.movierecommendation.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;


@Configuration
public class Routes {


    @Value("${movie-service.url}")
    private String movieServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return route("movie-service")
                .route(RequestPredicates.path("/api/movies"), http(movieServiceUrl))
                .build();
    }

}
