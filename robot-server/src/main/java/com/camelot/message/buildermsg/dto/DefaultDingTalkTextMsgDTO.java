package com.camelot.message.buildermsg.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[普通 文本消息]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 17:01
 */
@Data
@Accessors(chain = true)
public class DefaultDingTalkTextMsgDTO extends AbstractDefaultDingTalkMsgDTO {

    /**
     * 消息内容
     */
    private String content;
}
