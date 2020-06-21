package com.camelot.message;

import com.camelot.message.buildermsg.ChatDingTalkMsgBuilder;
import com.camelot.message.buildermsg.DefaultDingTalkMsgBuilder;
import com.camelot.message.buildermsg.RobotDingTalkMsgBuilder;
import com.camelot.message.buildermsg.WorkDingTalkMsgBuilder;
import com.camelot.message.buildermsg.dto.AbstractChatDingTalkMsgDTO;
import com.camelot.message.buildermsg.dto.AbstractDefaultDingTalkMsgDTO;
import com.camelot.message.buildermsg.dto.AbstractDingTalkRobotMsgDTO;
import com.camelot.message.buildermsg.dto.AbstractWorkDingTalkMsgDTO;
import com.camelot.message.buildermsg.dto.ChatDingTalkCardMsgDTO;
import com.camelot.message.buildermsg.dto.ChatDingTalkLinkMsgDTO;
import com.camelot.message.buildermsg.dto.ChatDingTalkTextMsgDTO;
import com.camelot.message.buildermsg.dto.DefaultDingTalkCardMsgDTO;
import com.camelot.message.buildermsg.dto.DefaultDingTalkLinkMsgDTO;
import com.camelot.message.buildermsg.dto.DefaultDingTalkTextMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkCardMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkLinkMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkMarkDownMsgDTO;
import com.camelot.message.buildermsg.dto.RobotDingTalkTextMsgDTO;
import com.camelot.message.buildermsg.dto.WorkDingTalkCardMsgDTO;
import com.camelot.message.buildermsg.dto.WorkDingTalkLinkMsgDTO;
import com.camelot.message.buildermsg.dto.WorkDingTalkMarkdownMsgDTO;
import com.camelot.message.buildermsg.dto.WorkDingTalkOaMsgDTO;
import com.camelot.message.buildermsg.dto.WorkDingTalkTextMsgDTO;
import com.camelot.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;
import com.dingtalk.api.request.OapiRobotSendRequest;

/**
 * <p>
 * Description:[钉钉消息构建工具]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4 14:18
 */
public class DingTalkMsgBuilderUtil {

    /**
     * 功能描述: <br>
     * 〈工作通知消息〉
     * 
     * @Param:
     * @Return: 类型不存在返回 null
     * @Author: shihengfei
     * @Date: 2020/6/4 14:22
     */
    public static OapiMessageCorpconversationAsyncsendV2Request buildWorkMsgRequest(AbstractWorkDingTalkMsgDTO dto) {
        OapiMessageCorpconversationAsyncsendV2Request request = getAsyncsendV2Request(dto);
        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        DingTalkMsgTypeEnum typeEnum = DingTalkMsgTypeEnum.dingTalkMsgTypeEnums.get(dto.getMsgType());
        switch (typeEnum) {
            case TEXT_MSG:
                WorkDingTalkMsgBuilder.buildTextMsg((WorkDingTalkTextMsgDTO)dto, msg);
                break;
            case LINK_MSG:
                WorkDingTalkMsgBuilder.buildLinkMsg((WorkDingTalkLinkMsgDTO)dto, msg);
                break;
            case MARKDOWN_MSG:
                WorkDingTalkMsgBuilder.buildMarkdownMsg((WorkDingTalkMarkdownMsgDTO)dto, msg);
                break;
            case ACTION_CARD_MSG:
                WorkDingTalkMsgBuilder.buildActionCardMsg((WorkDingTalkCardMsgDTO)dto, msg);
                break;
            case OA_MSG:
                WorkDingTalkMsgBuilder.buildOaMsg((WorkDingTalkOaMsgDTO)dto, msg);
                break;
            default:
                return null;
        }

        request.setMsg(msg);
        return request;
    }

    /**
     * 功能描述: <br>
     * 〈初始化请求〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/4 14:26
     */
    private static OapiMessageCorpconversationAsyncsendV2Request getAsyncsendV2Request(AbstractWorkDingTalkMsgDTO dto) {
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(dto.getUserIdList());
        request.setDeptIdList(dto.getDeptIdList());
        request.setAgentId(dto.getAgentId());
        request.setToAllUser(dto.getToAllUser());
        return request;
    }

    /**
     * 功能描述: <br>
     * 〈群消息构建〉
     * 
     * @Param:
     * @Return: 类型不存在返回 null
     * @Author: shihengfei
     * @Date: 2020/6/4 14:30
     */
    public static OapiChatSendRequest buildChatMsgRequest(AbstractChatDingTalkMsgDTO dto) {
        OapiChatSendRequest request = new OapiChatSendRequest();
        request.setChatid(dto.getChatid());
        OapiChatSendRequest.Msg msg = new OapiChatSendRequest.Msg();
        DingTalkMsgTypeEnum typeEnum = DingTalkMsgTypeEnum.dingTalkMsgTypeEnums.get(dto.getMsgType());
        switch (typeEnum) {
            case TEXT_MSG:
                ChatDingTalkMsgBuilder.buildTextMsg((ChatDingTalkTextMsgDTO)dto, msg);
                break;
            case LINK_MSG:
                ChatDingTalkMsgBuilder.buildLinkMsg((ChatDingTalkLinkMsgDTO)dto, msg);
                break;
            case ACTION_CARD_MSG:
                ChatDingTalkMsgBuilder.buildActionCardMsg((ChatDingTalkCardMsgDTO)dto, msg);
                break;
            default:
                return null;
        }
        request.setMsg(msg);
        return request;
    }

    /**
     * 功能描述: <br>
     * 〈普通消息〉
     * 
     * @Param:
     * @Return: 类型不存在返回 null
     * @Author: shihengfei
     * @Date: 2020/6/4 14:41
     */
    public static OapiMessageSendToConversationRequest buildDefaultMsgRequest(AbstractDefaultDingTalkMsgDTO dto) {
        OapiMessageSendToConversationRequest request = new OapiMessageSendToConversationRequest();
        request.setCid(dto.getCid());
        request.setSender(dto.getSender());
        OapiMessageSendToConversationRequest.Msg msg = new OapiMessageSendToConversationRequest.Msg();

        DingTalkMsgTypeEnum typeEnum = DingTalkMsgTypeEnum.dingTalkMsgTypeEnums.get(dto.getMsgType());
        switch (typeEnum) {
            case TEXT_MSG:
                DefaultDingTalkMsgBuilder.buildTextMsg((DefaultDingTalkTextMsgDTO)dto, msg);
                break;
            case LINK_MSG:
                DefaultDingTalkMsgBuilder.buildLinkMsg((DefaultDingTalkLinkMsgDTO)dto, msg);
                break;
            case ACTION_CARD_MSG:
                DefaultDingTalkMsgBuilder.buildActionCardMsg((DefaultDingTalkCardMsgDTO)dto, msg);
                break;
            default:
                return null;
        }
        request.setMsg(msg);
        return request;
    }

    /**
     * 功能描述: <br>
     * 〈机器人消息〉
     * 
     * @Param:
     * @Return: 类型不存在返回 null
     * @Author: shihengfei
     * @Date: 2020/6/4 14:41
     */
    public static OapiRobotSendRequest buildRobotMsgRequest(AbstractDingTalkRobotMsgDTO dto) {
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        DingTalkMsgTypeEnum typeEnum = DingTalkMsgTypeEnum.dingTalkMsgTypeEnums.get(dto.getMsgType());
        switch (typeEnum) {
            case TEXT_MSG:
                RobotDingTalkMsgBuilder.buildTextMsg((RobotDingTalkTextMsgDTO)dto, request);
                break;
            case MARKDOWN_MSG:
                RobotDingTalkMsgBuilder.buildMarkDownMsg((RobotDingTalkMarkDownMsgDTO)dto, request);
                break;
            case LINK_MSG:
                RobotDingTalkMsgBuilder.buildLinkMsg((RobotDingTalkLinkMsgDTO)dto, request);
                break;
            case ROBOT_ACTION_CARD_MSG:
                RobotDingTalkMsgBuilder.buildActionCardMsg((RobotDingTalkCardMsgDTO)dto, request);
                break;
            default:
                return null;
        }
        return request;
    }

}
