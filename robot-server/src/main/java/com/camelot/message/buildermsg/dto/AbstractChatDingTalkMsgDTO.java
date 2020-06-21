package com.camelot.message.buildermsg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[群消息公共部分]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 16:59
 */
@Data
@Accessors(chain = true)
public abstract class AbstractChatDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = 4007068166025501578L;
    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 群会话的id
     */
    private String chatid;

}
