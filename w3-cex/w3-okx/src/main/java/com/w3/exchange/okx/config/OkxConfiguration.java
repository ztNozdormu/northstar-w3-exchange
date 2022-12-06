package com.w3.exchange.okx.config;

import com.w3.exchange.okx.properties.OkxProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({OkxProperties.class})
@ConfigurationProperties(prefix = "w3-exchange")
@Data
public class OkxConfiguration {

    private OkxProperties okx;

    // TODO 项目初始化注入的配置bean对象
}
