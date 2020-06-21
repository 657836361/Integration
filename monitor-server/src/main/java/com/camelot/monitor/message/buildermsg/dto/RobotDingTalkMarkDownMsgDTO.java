package com.camelot.monitor.message.buildermsg.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * Description:[群 Markdown 消息]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 18:16
 */
@Data
public class RobotDingTalkMarkDownMsgDTO extends AbstractDingTalkRobotMsgDTO {

    /**
     * 首屏会话透出的展示内容
     */
    private String title;

    /**
     * markdown格式的消息
     */
    private String text;

    /**
     * 被@人的手机号
     */
    private List<String> atMobiles;

    /**
     * @ 所有人是true，否则为false
     */
    private String isAtAll;
}
