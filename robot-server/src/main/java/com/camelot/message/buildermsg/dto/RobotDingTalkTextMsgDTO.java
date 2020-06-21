package com.camelot.message.buildermsg.dto;

import java.util.List;

import lombok.Data;

/**
 * <p>
 * Description:[群机器人发送文本消息]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/17 11:02
 */
@Data
public class RobotDingTalkTextMsgDTO extends AbstractDingTalkRobotMsgDTO {

    /** 消息文本 */
    private String content;

    /** 被@人的手机号 */
    private List<String> atMobiles;

    /** @所有人是true，否则为false */
    private String isAtAll;
}
