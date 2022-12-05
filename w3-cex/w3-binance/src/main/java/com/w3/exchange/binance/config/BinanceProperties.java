package com.w3.exchange.binance.config;

import com.w3.exchange.common.config.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="binance")
public class BinanceProperties extends BaseProperties {
      // TODO 扩展字段
}
