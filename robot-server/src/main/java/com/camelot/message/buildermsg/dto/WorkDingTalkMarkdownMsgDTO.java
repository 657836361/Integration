package com.camelot.message.buildermsg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[工作Markdown消息]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public class WorkDingTalkMarkdownMsgDTO extends AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = 3045255639568066128L;

    /**
     * 首屏会话透出的展示内容
     */
    private String title;

    /**
     * markdown格式的消息，建议500字符以内
     */
    private String text;
}
