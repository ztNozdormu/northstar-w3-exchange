package io.github.ztnozdormu.cryptocompare.config;

import io.github.ztnozdormu.cryptocompare.properties.CompareProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties({CompareProperties.class})
@ConfigurationProperties(prefix = "w3-exchange")
@Data
@Component
public class CompareConfiguration {

    private CompareProperties compare;

    // TODO 项目初始化注入的配置bean对象

}
