package com.camelot.monitor.message.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * Description:[钉钉接口返回限制错误码]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/4/29 15:24
 */

public enum DingTalkErrorCodeEnum  {

    /** 单接口请求超限  */
    ERROR_CODE_90001("90001", "您的服务器调用钉钉开放平台所有接口的请求都被暂时禁用了"),
    ERROR_CODE_90002("90002", "您的服务器调用钉钉开放平台当前接口的所有请求都被暂时禁用了"),
    ERROR_CODE_90003("90003","您的企业调用钉钉开放平台所有接口的请求都被暂时禁用了，仅对企业自己的Accesstoken有效"),
    ERROR_CODE_90004("90004","您当前使用的CorpId及CorpSecret被暂时禁用了，仅对企业自己的Accesstoken有效"),
    ERROR_CODE_90005("90005","您的企业调用当前接口次数过多，请求被暂时禁用了，仅对企业自己的Accesstoken有效"),
    ERROR_CODE_90006("90006","您当前使用的CorpId及CorpSecret调用当前接口次数过多，请求被暂时禁用了，仅对企业自己的Accesstoken有效"),
    ERROR_CODE_90018("90018","每个企业的每个appkey调用单个接口的频率不可超过40次/秒")

            ;

    /**
     * code值
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    DingTalkErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * code：操作枚举
     */
    public static Map<String, DingTalkErrorCodeEnum> dingTalkErrorCodeEnums =
            Arrays.stream(values()).collect(Collectors.toMap(DingTalkErrorCodeEnum::getCode, re -> re));

}
