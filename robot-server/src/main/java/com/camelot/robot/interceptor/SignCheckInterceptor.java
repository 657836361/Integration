package com.camelot.robot.interceptor;

import java.util.Objects;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 钉钉请求合法性校验
 * 
 * @author guanpei
 * @date 2020/6/18
 */
@Component
@Slf4j
public class SignCheckInterceptor implements HandlerInterceptor {

    @Value("${dingTalk.robot.appSecret}")
    private String appSecret;

    /**
     * 一次请求超过1小时失效
     */
    private final static Long TIME_STAMP = 1000L * 60 * 60;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        boolean isPass = false;
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        if (StringUtils.isBlank(timestamp) || StringUtils.isBlank(sign)) {
            log.info("头信息为空");
            response.setStatus(400);
            return isPass;
        }
        long l = 0;
        try {
            l = Long.parseLong(timestamp);
        } catch (NumberFormatException e) {
            log.error("时间戳转化失败", e);
            response.setStatus(400);
            return isPass;
        }
        if ((System.currentTimeMillis() - l) <= TIME_STAMP) {
            String stringToSign = timestamp + "\n" + appSecret;
            String checkedSign = null;
            try {
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(new SecretKeySpec(appSecret.getBytes("UTF-8"), "HmacSHA256"));
                byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
                checkedSign = new String(Base64.encodeBase64(signData));
            } catch (Exception e) {
                log.error("解密sign失败", e);
                response.setStatus(500);
                return isPass;
            }
            return Objects.equals(sign, checkedSign);
        }
        response.setStatus(401);
        return isPass;
    }
}
