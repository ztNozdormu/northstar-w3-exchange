package com.w3.exchange.binance.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({BinanceProperties.class})
public class BinanceConfiguration {

    private BinanceProperties properties;

    public BinanceConfiguration(BinanceProperties properties) {
        this.properties = properties;
    }

    // TODO 项目初始化注入的配置bean对象
}
