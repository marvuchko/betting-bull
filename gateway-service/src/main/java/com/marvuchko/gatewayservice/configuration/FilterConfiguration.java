package com.marvuchko.gatewayservice.configuration;

import com.marvuchko.gatewayservice.filter.LogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public LogFilter logFilter() {
        return new LogFilter();
    }

}
