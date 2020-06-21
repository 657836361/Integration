package com.camelot.monitor.admin.server;

import com.camelot.monitor.message.DingTalkMsgBuilderUtil;
import com.camelot.monitor.message.buildermsg.dto.RobotDingTalkTextMsgDTO;
import com.camelot.monitor.message.dingtalk.DingTalkUtil;
import com.camelot.monitor.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiRobotSendRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.text.MessageFormat;

/**
 * <p>
 * Description:消费者接口
 * </p>
 *
 * @author guanpei
 * @version 1.0
 * @date Created on 2020/4/3
 */
@Service
public class ServerInstanceService {

    public static final String MESSAGE_BODY = "{0}服务状态变更,当前状态：{1}";

    @Value("${admin.webhook}")
    private String webhook;

    @Value("${admin.secret}")
    private String secret;

    /**
     * 执行相应任务
     *
     * @param serverName 服务名称
     * @param serverStatus 服务现在的状态
     */

    protected String buildMessage(String serverName, String serverStatus){
        return MessageFormat.format(MESSAGE_BODY, serverName, serverStatus);
    }

    protected void sendMessage(String message){
        RobotDingTalkTextMsgDTO robotDingTalkTextMsgDTO = new RobotDingTalkTextMsgDTO();
        robotDingTalkTextMsgDTO.setContent(message);
        robotDingTalkTextMsgDTO.setMsgType(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        OapiRobotSendRequest oapiRobotSendRequest = DingTalkMsgBuilderUtil.buildRobotMsgRequest(robotDingTalkTextMsgDTO);
        DingTalkUtil.sendRobotMsg(oapiRobotSendRequest,secret,webhook);
    }

    /**
     * 执行相应任务
     *
     * @param serverName 服务名称
     * @param serverStatus 服务现在的状态
     */
    public void execute(String serverName, String serverStatus){
        // 组装消息
        String message = buildMessage(serverName,serverStatus);

        // 发送消息
        sendMessage(message);
    }
}