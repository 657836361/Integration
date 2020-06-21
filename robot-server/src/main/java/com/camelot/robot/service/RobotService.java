package com.camelot.robot.service;

import com.camelot.robot.model.MessageDTO;
import com.dingtalk.api.request.OapiRobotSendRequest;

/**
 * <p>
 * Description:[]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
public interface RobotService {

    OapiRobotSendRequest getRobotNoticesByKeyWord(MessageDTO robotDTO);
}
