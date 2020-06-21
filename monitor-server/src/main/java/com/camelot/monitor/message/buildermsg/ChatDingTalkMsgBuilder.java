package com.camelot.monitor.message.buildermsg;

import com.camelot.monitor.message.buildermsg.dto.ChatDingTalkCardMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.ChatDingTalkLinkMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.ChatDingTalkTextMsgDTO;
import com.camelot.monitor.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiChatSendRequest;

/**
 * <p>
 * Description:[群消息构建]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 17:04
 */
public class ChatDingTalkMsgBuilder {

    /**
     * <p>
     * Description:[构建文本消息]
     * </p>
     * 
     * @author shf
     * @version 1.0
     * @date Created on 2020/3/16
     */
    public static OapiChatSendRequest.Msg buildTextMsg(ChatDingTalkTextMsgDTO dto, OapiChatSendRequest.Msg msg) {
        msg.setMsgtype(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        msg.setText(new OapiChatSendRequest.Text());
        msg.getText().setContent(dto.getContent());
        return msg;
    }

    /**
     * <p>
     * Description:[构建链接消息]
     * </p>
     * 
     * @author shf
     * @version 1.0
     * @date Created on 2020/3/16
     */
    public static OapiChatSendRequest.Msg buildLinkMsg(ChatDingTalkLinkMsgDTO dto, OapiChatSendRequest.Msg msg) {
        // 链接消息
        msg.setMsgtype(DingTalkMsgTypeEnum.LINK_MSG.getMsgType());
        msg.setLink(new OapiChatSendRequest.Link());
        msg.getLink().setTitle(dto.getTitle());
        msg.getLink().setText(dto.getText());
        msg.getLink().setMessageUrl(dto.getMessageUrl());
        msg.getLink().setPicUrl(dto.getPicUrl());
        return msg;
    }

    /**
     * <p>
     * Description:[构建卡片消息]
     * </p>
     * 
     * @author shf
     * @version 1.0
     * @date Created on 2020/3/16
     */
    public static OapiChatSendRequest.Msg buildActionCardMsg(ChatDingTalkCardMsgDTO dto, OapiChatSendRequest.Msg msg) {
        // 卡片消息
        msg.setMsgtype(DingTalkMsgTypeEnum.ACTION_CARD_MSG.getMsgType());
        msg.setActionCard(new OapiChatSendRequest.ActionCard());
        msg.getActionCard().setTitle(dto.getTitle());
        msg.getActionCard().setMarkdown(dto.getMarkdown());
        msg.getActionCard().setSingleTitle(dto.getSingleTitle());
        msg.getActionCard().setSingleUrl(dto.getSingleUrl());
        return msg;
    }
}
