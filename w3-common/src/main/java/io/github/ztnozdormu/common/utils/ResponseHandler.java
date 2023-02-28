package io.github.ztnozdormu.common.utils;

import io.github.ztnozdormu.common.exceptions.ExchangeClientException;
import io.github.ztnozdormu.common.exceptions.ExchangeConnectorException;
import io.github.ztnozdormu.common.exceptions.ExchangeServerException;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public final class ResponseHandler {

    private static final int HTTP_STATUS_CODE_400 = 400;
    private static final int HTTP_STATUS_CODE_499 = 499;
    private static final int HTTP_STATUS_CODE_500 = 500;

    private ResponseHandler() {
    }

    public static String handleResponse(Request request, boolean showLimitUsage) {
        try (Response response = OkHttpUtils.builder().okHttpClient.newCall(request).execute()) {//OkHttpUtils.builder().okHttpClient
            String responseAsString = getResponseBodyAsString(response.body());

            if (response.code() >= HTTP_STATUS_CODE_400 && response.code() <= HTTP_STATUS_CODE_499) {
                throw handleErrorResponse(responseAsString, response.code());
            } else if (response.code() >= HTTP_STATUS_CODE_500) {
                throw new ExchangeServerException(responseAsString, response.code());
            }

            if (showLimitUsage) {
                return getlimitUsage(response, responseAsString);
            } else {
                return responseAsString;
            }
        } catch (IOException | IllegalStateException e) {
            throw new ExchangeConnectorException("[ResponseHandler] OKHTTP Error: " + e.getMessage());
        }
    }

    private static String getlimitUsage(Response response, String resposeBodyAsString) {
        JSONObject json = new JSONObject();
        json.put("x-sapi-used-ip-weight-1m", response.header("X-SAPI-USED-IP-WEIGHT-1M"));
        json.put("x-mbx-used-weight", response.header("x-mbx-used-weight"));
        json.put("x-mbx-used-weight-1m", response.header("x-mbx-used-weight-1m"));
        json.put("data", resposeBodyAsString);

        return json.toString();
    }

    private static ExchangeClientException handleErrorResponse(String responseBody, int responseCode) {
        try {
            String errorMsg = JSONParser.getJSONStringValue(responseBody, "msg");
            int errorCode = JSONParser.getJSONIntValue(responseBody, "code");
            return new ExchangeClientException(responseBody, errorMsg, responseCode, errorCode);
        } catch (JSONException e) {
            throw new ExchangeClientException(responseBody, responseCode);
        }
    }

    private static String getResponseBodyAsString(ResponseBody body) throws IOException {
        if (null != body) {
            return body.string();
        } else {
            return "";
        }
    }
}
