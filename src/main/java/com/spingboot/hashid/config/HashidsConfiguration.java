package com.spingboot.hashid.config;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashidsConfiguration {

    @Bean
    public Hashids hashids() {
        return new Hashids("abc", 8,"abcdefghijklmnopqrstuvwxyz");
    }
}
