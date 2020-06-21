package com.camelot.message.buildermsg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[工作文本消息]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public class WorkDingTalkTextMsgDTO extends AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = 2553843371573715334L;

    /**
     * 消息内容，建议500字符以内
     */
    private String content;

}
