package com.camelot.message.buildermsg.dto;

import lombok.Data;

/**
 * <p>
 * Description:[钉钉群机器人消息公共部分]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 16:59
 */
@Data
public abstract class AbstractDingTalkRobotMsgDTO {

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 群会话的 webhook
     */
    private String webhook;

    /***
     * appSecret
     */
    private String appSecret;

}
