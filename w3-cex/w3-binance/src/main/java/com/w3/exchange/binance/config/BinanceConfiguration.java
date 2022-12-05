package com.w3.exchange.binance.config;

import com.w3.exchange.binance.properties.BinanceProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({BinanceProperties.class})
@ConfigurationProperties(prefix = "w3-exchange")
@Data
public class BinanceConfiguration {

    private BinanceProperties binance;
    
    // TODO 项目初始化注入的配置bean对象
}
