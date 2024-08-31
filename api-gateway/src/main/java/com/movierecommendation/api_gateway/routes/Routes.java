package com.movierecommendation.api_gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Routes {

    @Value("${user-service.url}")
    private String userServiceUrl;

    @Value("${movie-service.url}")
    private String movieServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> userRoutes() {
        return route(RequestPredicates.path("/api/users/**"),
                req -> ServerResponse.permanentRedirect(
                        URI.create( userServiceUrl + req.uri().getPath())
                ).build());
    }

    @Bean
    public RouterFunction<ServerResponse> movieRoutes() {
        return route(RequestPredicates.path("/api/movies/**"),
                req -> ServerResponse.permanentRedirect(
                        URI.create(movieServiceUrl + req.uri().getPath())
                ).build());
    }

}
