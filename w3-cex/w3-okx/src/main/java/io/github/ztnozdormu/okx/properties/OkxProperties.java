package io.github.ztnozdormu.okx.properties;

import io.github.ztnozdormu.common.properties.BaseProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
