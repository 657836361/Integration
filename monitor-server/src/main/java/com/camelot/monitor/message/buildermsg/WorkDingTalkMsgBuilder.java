package com.camelot.monitor.message.buildermsg;


import com.camelot.monitor.message.buildermsg.dto.WorkDingTalkCardMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.WorkDingTalkLinkMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.WorkDingTalkMarkdownMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.WorkDingTalkOaMsgDTO;
import com.camelot.monitor.message.buildermsg.dto.WorkDingTalkTextMsgDTO;
import com.camelot.monitor.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;

/**
 * <p>
 * Description:[工作通知消息构建]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
public class WorkDingTalkMsgBuilder {

    /**
     * 功能描述: <br>
     * 〈构建文本消息〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 11:43
     */
    public static OapiMessageCorpconversationAsyncsendV2Request.Msg buildTextMsg(WorkDingTalkTextMsgDTO dto,
                                                                                 OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        msg.setMsgtype(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent(dto.getContent());
        return msg;
    }

    /**
     * 功能描述: <br>
     * 〈构建链接消息〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 11:44
     */
    public static OapiMessageCorpconversationAsyncsendV2Request.Msg buildLinkMsg(WorkDingTalkLinkMsgDTO dto,
                                                                                 OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        // 链接消息
        msg.setMsgtype(DingTalkMsgTypeEnum.LINK_MSG.getMsgType());
        msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
        msg.getLink().setTitle(dto.getTitle());
        msg.getLink().setText(dto.getText());
        msg.getLink().setMessageUrl(dto.getMessageUrl());
        msg.getLink().setPicUrl(dto.getPicUrl());
        return msg;
    }

    /**
     * 功能描述: <br>
     * 〈构建markdown消息〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 11:44
     */
    public static OapiMessageCorpconversationAsyncsendV2Request.Msg buildMarkdownMsg(WorkDingTalkMarkdownMsgDTO dto,
                                                                                     OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        msg.setMsgtype(DingTalkMsgTypeEnum.MARKDOWN_MSG.getMsgType());
        msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
        msg.getMarkdown().setText(dto.getText());
        msg.getMarkdown().setTitle(dto.getTitle());
        return msg;
    }

    /**
     * 功能描述: <br>
     * 〈构建卡片消息〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 11:44
     */
    public static OapiMessageCorpconversationAsyncsendV2Request.Msg buildActionCardMsg(WorkDingTalkCardMsgDTO dto,
                                                                                       OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        // 卡片消息
        msg.setMsgtype(DingTalkMsgTypeEnum.ACTION_CARD_MSG.getMsgType());
        msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        msg.getActionCard().setTitle(dto.getTitle());
        msg.getActionCard().setMarkdown(dto.getMarkdown());
        msg.getActionCard().setSingleTitle(dto.getSingleTitle());
        msg.getActionCard().setSingleUrl(dto.getSingleUrl());
        return msg;
    }

    /**
     * 功能描述: <br>
     * 〈构建OA消息〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 11:51
     */
    public static OapiMessageCorpconversationAsyncsendV2Request.Msg buildOaMsg(WorkDingTalkOaMsgDTO dto,
                                                                               OapiMessageCorpconversationAsyncsendV2Request.Msg msg) {
        msg.setMsgtype(DingTalkMsgTypeEnum.OA_MSG.getMsgType());
        msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
        msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
        msg.getOa().getHead().setText(dto.getText());
        msg.getOa().getHead().setBgcolor(dto.getBgColor());
        msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
        msg.getOa().getBody().setContent(dto.getContent());
        msg.getOa().getBody().setForm(dto.getForm());
        msg.getOa().getBody().setTitle(dto.getTitle());
        msg.getOa().getBody().setAuthor(dto.getAuthor());
        msg.getOa().getBody().setFileCount(dto.getFileCount());
        msg.getOa().getBody().setRich(dto.getRich());
        msg.getOa().getBody().setImage(dto.getImage());
        return msg;
    }

}
