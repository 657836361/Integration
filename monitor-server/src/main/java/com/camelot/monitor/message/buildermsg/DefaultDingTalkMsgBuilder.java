package com.camelot.monitor.message.buildermsg;


import com.camelot.monitor.message.buildermsg.dto.DefaultDingTalkCardMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.DefaultDingTalkLinkMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.DefaultDingTalkTextMsgDTO;
import com.camelot.monitor.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;

/**
 * <p>
 * Description:[普通消息构建]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
public class DefaultDingTalkMsgBuilder {

    /**
     * <p>
     * Description:[构建文本消息]
     * </p>
     * 
     * @author shf
     * @version 1.0
     * @date Created on 2020/3/16
     */
    public static OapiMessageSendToConversationRequest.Msg buildTextMsg(DefaultDingTalkTextMsgDTO dto,
                                                                        OapiMessageSendToConversationRequest.Msg msg) {
        msg.setMsgtype(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        msg.setText(new OapiMessageSendToConversationRequest.Text());
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
    public static OapiMessageSendToConversationRequest.Msg buildLinkMsg(DefaultDingTalkLinkMsgDTO dto,
                                                                        OapiMessageSendToConversationRequest.Msg msg) {
        // 链接消息
        msg.setMsgtype(DingTalkMsgTypeEnum.LINK_MSG.getMsgType());
        msg.setLink(new OapiMessageSendToConversationRequest.Link());
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
    public static OapiMessageSendToConversationRequest.Msg buildActionCardMsg(DefaultDingTalkCardMsgDTO dto,
                                                                              OapiMessageSendToConversationRequest.Msg msg) {
        // 卡片消息
        msg.setMsgtype(DingTalkMsgTypeEnum.ACTION_CARD_MSG.getMsgType());
        msg.setActionCard(new OapiMessageSendToConversationRequest.ActionCard());
        msg.getActionCard().setTitle(dto.getTitle());
        msg.getActionCard().setMarkdown(dto.getMarkdown());
        msg.getActionCard().setSingleTitle(dto.getSingleTitle());
        msg.getActionCard().setSingleUrl(dto.getSingleUrl());
        return msg;
    }
}
