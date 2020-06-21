package com.camelot.message.buildermsg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[普通消息公共部分]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public abstract class AbstractDefaultDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = -5727899955576255007L;
    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息发送者 userId
     */
    private String sender;

    /**
     * 群会话或者个人会话的id，通过JSAPI接口唤起联系人界面选择会话获取会话cid
     */
    private String cid;

}
