package com.w3.exchange.common.utils;

import com.w3.exchange.common.enums.HttpMethod;
import com.w3.exchange.common.exceptions.ExchangeConnectorException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public final class RequestBuilder {
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    private RequestBuilder() {
    }

    public static Request.Builder buildPublicRequest(String fullUrl, HttpMethod httpMethod) {
        try {
            final Request.Builder result;
            switch (httpMethod) {
                case POST:
                    result = OkHttpUtils.builder()
                            .url(fullUrl)
                            .post(true)
                            .sync();
                    break;
                case GET:
                    result = OkHttpUtils.builder()
                            .url(fullUrl)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .get()
                            .sync();
                    break;
                case PUT:
                    result = OkHttpUtils.builder()
                            .url(fullUrl)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .post(false)
                            .sync();
                    break;
                case DELETE:
                    result = OkHttpUtils.builder()
                            .url(fullUrl)
                            .post(false)
                            .sync();
                    break;
                default:
                    throw new ExchangeConnectorException("Invalid HTTP method: " + httpMethod);
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw new ExchangeConnectorException("Invalid URL: " + e.getMessage());
        }
    }

    public static Request buildApiKeyRequest(String fullUrl, HttpMethod httpMethod, String apiKey) {
        try {
            final Request request;
            switch (httpMethod) {
                case POST:
                    request = new Request.Builder().url(fullUrl)
                            .post(RequestBody.create("", JSON_TYPE))
                            .addHeader("X-MBX-APIKEY", apiKey)
                            .build();
                    break;
                case GET:
                    request = new Request.Builder().url(fullUrl)
                            .get()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("X-MBX-APIKEY", apiKey)
                            .build();
                    break;
                case PUT:
                    request = new Request.Builder().url(fullUrl)
                            .put(RequestBody.create("", JSON_TYPE))
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("X-MBX-APIKEY", apiKey)
                            .build();
                    break;
                case DELETE:
                    request = new Request.Builder().url(fullUrl)
                            .delete()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("X-MBX-APIKEY", apiKey)
                            .build();
                    break;
                default:
                    throw new ExchangeConnectorException("Invalid HTTP method: " + httpMethod);
            }
            return request;
        } catch (IllegalArgumentException e) {
            throw new ExchangeConnectorException("Invalid URL: " + e.getMessage());
        }
    }

    public static Request buildWebsocketRequest(String fullUrl) {
        return new Request.Builder().url(fullUrl).build();
    }

}
