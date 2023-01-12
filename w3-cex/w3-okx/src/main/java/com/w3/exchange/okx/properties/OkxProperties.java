package com.w3.exchange.okx.properties;

import com.w3.exchange.common.properties.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties
public class OkxProperties extends BaseProperties {
    /**
     * 密码串
     */
      public String passPhrase;
    /**
     *
     */
    public boolean isSimluate = false;
}
