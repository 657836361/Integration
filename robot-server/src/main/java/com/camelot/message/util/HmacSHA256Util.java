package com.camelot.message.util;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description:[群机器人消息加签]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/4/9 11:16
 */
@Slf4j
public class HmacSHA256Util {

    public static String getSign(Long timestamp, String secret) {
        String sign = "";
        try {
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        } catch (Exception e) {
            log.error("群机器人发送消息加签异常:{}", e.getMessage());
        }
        return sign;
    }
}
