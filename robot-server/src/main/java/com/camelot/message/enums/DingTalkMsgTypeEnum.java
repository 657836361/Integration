package com.camelot.message.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * Description:[钉钉消息类型枚举]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Getter
@AllArgsConstructor
public enum DingTalkMsgTypeEnum {

    /**
     * 文本消息
     */
    TEXT_MSG("text"),

    /**
     * 链接消息
     */
    LINK_MSG("link"),

    /**
     * markdown消息
     */
    MARKDOWN_MSG("markdown"),

    /**
     * oa 消息
     */
    OA_MSG("oa"),

    /**
     * 卡片消息
     */
    ACTION_CARD_MSG("action_card"),

    /**
     * 群机器人卡片消息
     */
    ROBOT_ACTION_CARD_MSG("actionCard"),

    ;

    /**
     * 消息类型
     */
    private final String msgType;

    /**
     * code：操作枚举
     */
    public static Map<String, DingTalkMsgTypeEnum> dingTalkMsgTypeEnums =
        Arrays.stream(values()).collect(Collectors.toMap(DingTalkMsgTypeEnum::getMsgType, re -> re));

}
