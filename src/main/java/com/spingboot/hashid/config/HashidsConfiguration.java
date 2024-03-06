package com.spingboot.hashid.config;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashidsConfiguration {

    @Bean
    public Hashids hashids() {
        return new Hashids("k3G7QAe51FCsPW92uEOyq4Bg6Sp8YzVTmnU0liwDdHXLajZrfxNhobJIRcMvKt", 6,"0123456789abcdefghijklmnopqrstuvwxyz");
    }
}
