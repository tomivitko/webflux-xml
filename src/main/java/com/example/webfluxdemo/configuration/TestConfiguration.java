package com.example.webfluxdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TestConfiguration {

    @Bean
    public WebClient webClient() {
        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder());
                    configurer.defaultCodecs().jaxb2Encoder(new Jaxb2XmlEncoder());
                })
                .build();
        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .build();
    }
}
