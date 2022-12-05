package com.w3.exchange.common.domain;

import com.w3.exchange.common.utils.RequestHandler;
import lombok.Data;

@Data
public class BaseDO {
    public  String baseUrl;
    public RequestHandler requestHandler;
    public  boolean showLimitUsage;
}
