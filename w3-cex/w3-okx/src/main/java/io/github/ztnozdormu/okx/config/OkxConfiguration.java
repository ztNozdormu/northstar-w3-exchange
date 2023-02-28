package io.github.ztnozdormu.okx.config;

import io.github.ztnozdormu.okx.properties.OkxProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties({OkxProperties.class})
@ConfigurationProperties(prefix = "w3-exchange")
@Data
@Component
public class OkxConfiguration {

    private OkxProperties okx;

    // TODO 项目初始化注入的配置bean对象

}
