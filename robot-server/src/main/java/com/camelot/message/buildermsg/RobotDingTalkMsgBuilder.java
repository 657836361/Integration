package com.camelot.message.buildermsg;

import com.camelot.message.buildermsg.dto.RobotDingTalkCardMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkLinkMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkMarkDownMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkTextMsgDTO;
import com.dingtalk.api.request.OapiRobotSendRequest;

/**
 * <p>
 * Description:[钉钉群机器消息构建]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 17:04
 */
public class RobotDingTalkMsgBuilder {

    /**
     * <p>
     * Description:[构建链接消息]
     * </p>
     * 
     * @author shf
     * @version 1.0
     * @date Created on 2020/3/16
     */
    public static OapiRobotSendRequest buildLinkMsg(RobotDingTalkLinkMsgDTO robotSendMsgDTO,
                                                    OapiRobotSendRequest request) {
        // 链接消息
        request.setMsgtype(robotSendMsgDTO.getMsgType());
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl(robotSendMsgDTO.getMessageUrl());
        link.setPicUrl(robotSendMsgDTO.getPicUrl());
        link.setTitle(robotSendMsgDTO.getTitle());
        link.setText(robotSendMsgDTO.getText());
        request.setLink(link);
        return request;
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
    public static OapiRobotSendRequest buildActionCardMsg(RobotDingTalkCardMsgDTO dto, OapiRobotSendRequest request) {
        request.setMsgtype(dto.getMsgType());
        OapiRobotSendRequest.Actioncard actioncard = new OapiRobotSendRequest.Actioncard();
        // 卡片消息
        actioncard.setText(dto.getMarkdown());
        actioncard.setTitle(dto.getTitle());
        actioncard.setSingleTitle(dto.getSingleTitle());
        actioncard.setSingleURL(dto.getSingleUrl());
        request.setActionCard(actioncard);
        return request;
    }

    /**
     * 功能描述: <br>
     * 〈构建文本消息〉
     * 
     * @Author: shihengfei
     * @Date: 2020/6/17 11:08
     */
    public static OapiRobotSendRequest buildTextMsg(RobotDingTalkTextMsgDTO dto, OapiRobotSendRequest request) {
        request.setMsgtype(dto.getMsgType());
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(dto.getAtMobiles());
        at.setIsAtAll(dto.getIsAtAll());
        request.setAt(at);
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        // 文本消息
        text.setContent(dto.getContent());
        request.setText(text);
        return request;
    }

    public static OapiRobotSendRequest buildMarkDownMsg(RobotDingTalkMarkDownMsgDTO dto, OapiRobotSendRequest request) {
        request.setMsgtype(dto.getMsgType());
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(dto.getAtMobiles());
        at.setIsAtAll(dto.getIsAtAll());
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        // 设置markdown消息
        markdown.setText(dto.getText());
        markdown.setTitle(dto.getTitle());
        request.setMarkdown(markdown);
        return request;
    }
}
