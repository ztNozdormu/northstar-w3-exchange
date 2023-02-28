package io.github.ztnozdormu.common.domain;

import io.github.ztnozdormu.common.utils.RequestHandler;
import lombok.Data;

@Data
public class BaseDO {
    public String baseUrl;
    public RequestHandler requestHandler;
    public boolean showLimitUsage;
}
