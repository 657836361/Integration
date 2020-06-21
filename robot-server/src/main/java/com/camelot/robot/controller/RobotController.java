package com.camelot.robot.controller;

import com.camelot.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camelot.robot.model.MessageDTO;
import com.dingtalk.api.request.OapiRobotSendRequest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description:[controller demo]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
@Api(tags = "robot")
@Slf4j
@RestController
@RequestMapping("/recieve")
public class RobotController {

    @Autowired
    private RobotService robotService;

    @PostMapping("")
    public OapiRobotSendRequest getRobotNoticesByKeyWord(@RequestBody MessageDTO robotDTO) {
        return robotService.getRobotNoticesByKeyWord(robotDTO);
    }

}
