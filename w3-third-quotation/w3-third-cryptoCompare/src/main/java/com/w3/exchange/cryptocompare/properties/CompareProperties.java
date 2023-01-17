package com.w3.exchange.cryptocompare.properties;

import com.w3.exchange.common.properties.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class CompareProperties extends BaseProperties {
    /**
     * 密码串
     */
      public String passPhrase;
    /**
     *
     */
    public boolean isSimluate = false;
}
