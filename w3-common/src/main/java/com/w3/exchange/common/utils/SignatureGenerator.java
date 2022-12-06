package com.w3.exchange.common.utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class SignatureGenerator {
    private static final String HMAC_SHA256 = "HmacSHA256";

    private SignatureGenerator() {
    }

    public static String getSignature(String data, String key) {
        byte[] hmacSha256;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return Hex.encodeHexString(hmacSha256);
    }

    /**
     * OK-ACCESS-SIGN的请求头是对timestamp + method + requestPath + body字符串（+表示字符串连接），以及SecretKey，使用HMAC SHA256方法加密，通过Base-64编码输出而得到的。
     * <p>
     * 如：sign=CryptoJS.enc.Base64.stringify(CryptoJS.HmacSHA256(timestamp + 'GET' + '/users/self/verify', SecretKey))
     * <p>
     * 其中，timestamp的值与OK-ACCESS-TIMESTAMP请求头相同，为ISO格式，如2020-12-08T09:08:57.715Z。
     * <p>
     * method是请求方法，字母全部大写：GET/POST。
     * <p>
     * requestPath是请求接口路径。如：/api/v5/account/balance
     * <p>
     * body是指请求主体的字符串，如果请求没有主体（通常为GET请求）则body可省略。如：{"instId":"BTC-USDT","lever":"5","mgnMode":"isolated"}
     **/
    public static String getOkxSignature(String timeStamp, String requestMethod, String requestBody, String requestPath, String secertKey) {
        String signStr = timeStamp + requestMethod + requestPath + requestBody;
        String result = null;
        try {
            Mac hmac_sha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secertKey.getBytes(), "HmacSHA256");
            hmac_sha256.init(secret_key);
            result = Base64.getEncoder().encodeToString(hmac_sha256.doFinal(signStr.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        String data = 1954516555 + "GET" + "/users/self/verify" + "999999";
        String sigStr1 = getSignature(data, "aaaaddsfhsdhfjsdf");
        String sigStr2 = getOkxSignature("1954516555","GET","/users/self/verify","999999","aaaaddsfhsdhfjsdf");

        System.out.println(sigStr1);
        System.out.println(sigStr2);
    }
}
