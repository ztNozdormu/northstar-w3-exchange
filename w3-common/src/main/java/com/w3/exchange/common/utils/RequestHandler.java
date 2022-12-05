package com.w3.exchange.common.utils;


import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.enums.RequestType;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;

import java.util.LinkedHashMap;
@Slf4j
public class RequestHandler {
    private final String apiKey;
    private final String secretKey;

    public RequestHandler(String apiKey) {
        this.apiKey = apiKey;
        this.secretKey = null;
    }

    public RequestHandler(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    /**
     * Build request based on request type and send the requests to server.
     * @param baseUrl
     * @param urlPath
     * @param signature
     * @param parameters
     * @param httpMethod
     * @param requestType
     * @return String - response from server
     */
    private String sendApiRequest(String baseUrl, String urlPath, String signature, LinkedHashMap<String, Object> parameters,
                                  HttpMethod httpMethod, RequestType requestType, boolean showLimitUsage) {
        String fullUrl = UrlBuilder.buildFullUrl(baseUrl, urlPath, parameters, signature);
        log.info("{} {}", httpMethod, fullUrl);
        Request request;
        switch (requestType) {
            case PUBLIC:
                request = RequestBuilder.buildPublicRequest(fullUrl, httpMethod).build();
                break;
            case WITH_API_KEY:
            case SIGNED:
                request = RequestBuilder.buildApiKeyRequest(fullUrl, httpMethod, apiKey);
                break;
            default:
                throw new ExchangeConnectorException("[RequestHandler] Invalid request type: " + requestType);
        }
        return ResponseHandler.handleResponse(request, showLimitUsage);
    }

    public String sendPublicRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters,
                                    HttpMethod httpMethod, boolean showLimitUsage) {
        return sendApiRequest(baseUrl, urlPath, null, parameters, httpMethod, RequestType.PUBLIC, showLimitUsage);
    }

    public String sendWithApiKeyRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters,
                                        HttpMethod httpMethod, boolean showLimitUsage) {
        if (null == apiKey || apiKey.isEmpty()) {
            throw new ExchangeConnectorException("[RequestHandler] API key cannot be null or empty!");
        }
        return sendApiRequest(baseUrl, urlPath, null, parameters, httpMethod, RequestType.WITH_API_KEY, showLimitUsage);
    }

    public String sendSignedRequest(String baseUrl, String urlPath, LinkedHashMap<String, Object> parameters,
                                    HttpMethod httpMethod, boolean showLimitUsage) {
        if (null == secretKey || secretKey.isEmpty() || null == apiKey || apiKey.isEmpty()) {
            throw new ExchangeConnectorException("[RequestHandler] Secret key/API key cannot be null or empty!");
        }
        parameters.put("timestamp", UrlBuilder.buildTimestamp());
        String queryString = UrlBuilder.joinQueryParameters(parameters);
        String signature = SignatureGenerator.getSignature(queryString, secretKey);
        return sendApiRequest(baseUrl, urlPath, signature, parameters, httpMethod, RequestType.SIGNED, showLimitUsage);
    }
}
