package com.camelot.message.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.camelot.common.exceptions.enums.IResponseEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * Description:[钉钉API响应错误码消息提示枚举]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@AllArgsConstructor
@Getter
public enum DingTalkErrorCodeMsgEnum implements IResponseEnum {

    /** 系统繁忙 */
    ERROR_CODE_ONE(-1, "操作超时，请稍后重试"),
    /** 无效的USERID */
    ERROR_CODE_33012(33012, "【${serverName}】无效的用户"),
    /** 无效的会话id */
    ERROR_CODE_34001(34001, "【${serverName}】无效的会话id"),
    /** 找不到群会话对象 */
    ERROR_CODE_34013(34013, "【${serverName}】群不存在"),
    /** 消息内容长度超过限制 */
    ERROR_CODE_34016(34016, "【${serverName}】消息内容长度超过限制"),
    /** 不合法的UserID */
    ERROR_CODE_40003(40003, "【${serverName}】不合法的用户"),
    /** 临时授权码失效 */
    ERROR_CODE_42008(42008, "【${serverName}】临时授权码失效"),
    /** 不存在的员工 */
    ERROR_CODE_46004(46004, "【${serverName}】不存在的员工"),
    /** 缺少chatid */
    ERROR_CODE_49000(49000, "【${serverName}】缺少chatid"),
    /** 操作者必须为群主 */
    ERROR_CODE_49003(49003, "【${serverName}】操作者必须为群主"),
    /** 不合法的群名称 */
    ERROR_CODE_49015(49015, "【${serverName}】不合法的群名称"),
    /** 没有调用接口的权限 */
    ERROR_CODE_60011(60011, "【${serverName}】没有调用接口的权限"),
    /** 用户已禁用 */
    ERROR_CODE_60120(60120, "【${serverName}】用户已禁用"),
    /** 找不到该用户 */
    ERROR_CODE_60121(60121, "【${serverName}】找不到该用户"),
    /** 鉴权异常 */
    ERROR_CODE_88(88, "【${serverName}】鉴权异常");

    /**
     * code值
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;

    @Override
    public String getSuccess() {
        return null;
    }

    /**
     * code：操作枚举
     */
    public static Map<Integer, DingTalkErrorCodeMsgEnum> dingTalkErrorCodeEnums =
        Arrays.stream(values()).collect(Collectors.toMap(DingTalkErrorCodeMsgEnum::getCode, re -> re));

}
