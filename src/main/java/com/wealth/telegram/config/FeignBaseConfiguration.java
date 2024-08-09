package com.wealth.telegram.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class FeignBaseConfiguration {

    @Autowired
    private FeignRequestInterceptor feignRequestInterceptor;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return feignRequestInterceptor;
    }

    @Bean
    @Profile({"local"})
    public RequestInterceptor localRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("power_by", "WTPOS");
        };
    }

}
