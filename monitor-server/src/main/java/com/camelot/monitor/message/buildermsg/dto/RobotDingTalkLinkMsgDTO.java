package com.camelot.monitor.message.buildermsg.dto;

import lombok.Data;

/**
 * <p>
 * Description:[群机器人消息发送入参]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/4/14 11:18
 */
@Data
public class RobotDingTalkLinkMsgDTO extends AbstractDingTalkRobotMsgDTO {

    /** 消息链接 */
    private String messageUrl;

    /** 图片链接 */
    private String picUrl;

    /** 标题 */
    private String title;

    /** 内容 */
    private String text;

}
