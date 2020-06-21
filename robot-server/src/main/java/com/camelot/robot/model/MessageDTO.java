package com.camelot.robot.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author guanpei
 * @date 2020/6/18
 */
@Data
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = -8351858090308279022L;
    /**
     * 目前只支持text
     */
    private String msgtype;
    /**
     * 消息文本
     */
    private TextDTO text;
    /**
     *
     * 加密的消息ID
     */
    private String msgId;
    /**
     * 消息的时间戳，单位ms
     */
    private String createAt;
    /**
     * 1-单聊、2-群聊
     */
    private String conversationType;
    /**
     * 加密的会话ID
     */
    private String conversationId;
    /**
     * 会话标题（群聊时才有）
     */
    private String conversationTitle;
    /**
     * 加密的发送者ID
     */
    private String senderId;
    /**
     * 发送者昵称
     */
    private String senderNick;
    /**
     * 发送者当前群的企业corpId（企业内部群有）
     */
    private String senderCorpId;
    /**
     * 发送者在企业内的userid（企业内部群有）
     */
    private String senderStaffId;
    /**
     * 加密的机器人ID
     */
    private String chatbotUserId;
    /**
     * 被@人的信息
     */
    private List<AtUser> atUsers;

}
