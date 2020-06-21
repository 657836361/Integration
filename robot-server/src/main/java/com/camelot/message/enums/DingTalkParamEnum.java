package com.camelot.message.enums;

/**
 * <p>
 * Description:[钉钉接口API枚举]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/5/11
 */
public enum DingTalkParamEnum {

    /**
     * 获取token
     */
    ACCESS_TOKEN("获取token", "https://oapi.dingtalk.com/gettoken", "GET"),

    /**
     * 创建用户
     */
    CREATE_USER("创建用户", "https://oapi.dingtalk.com/user/create", "POST"),

    /**
     * 更新用户
     */
    UPDATE_USER("更新用户", "https://oapi.dingtalk.com/user/update", "POST"),

    /**
     * 删除用户
     */
    DELETE_USER("删除用户", "https://oapi.dingtalk.com/user/delete", "GET"),

    /**
     * 通过手机号获取用户id
     */
    GET_USER_ID("通过手机号获取用户id", "https://oapi.dingtalk.com/user/get_by_mobile", "GET"),

    /**
     * 获取用户详细信息
     */
    GET_USER("获取用户详细信息", "https://oapi.dingtalk.com/user/get", "GET"),

    /**
     * 根据authCode获取用户id
     */
    GET_USER_ID_AUTH_CODE("根据授权码获取用户id", "https://oapi.dingtalk.com/user/getuserinfo", "GET"),

    /**
     * 根据临时授权码获取用户信息
     */
    GET_USERID_BY_UNIONID("根据临时授权码获取用户信息", "https://oapi.dingtalk.com/user/getUseridByUnionid", "GET"),

    /**
     * 根据钉钉扫码授权码获取用户信息
     */
    GET_USERINFO_BY_CODE("钉钉扫码获取用户信息", "https://oapi.dingtalk.com/sns/getuserinfo_bycode", "POST"),

    /**
     * 工作通知
     */
    SEND_WORK_MSG("工作通知", "https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2", "POST"),

    /**
     * 群消息
     */
    SEND_CHAT_MSG("群消息", "https://oapi.dingtalk.com/chat/send", "POST"),

    ;

    /**
     * 调用钉钉的方法
     */
    private String serverName;

    /**
     * url
     */
    private String url;

    /**
     * 请求方式
     */
    private String httpsType;

    DingTalkParamEnum(String serverName, String url, String httpsType) {
        this.serverName = serverName;
        this.url = url;
        this.httpsType = httpsType;
    }

    public String getServerName() {
        return serverName;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpsType() {
        return httpsType;
    }

}
