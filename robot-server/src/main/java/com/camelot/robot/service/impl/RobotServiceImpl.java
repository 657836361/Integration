package com.camelot.robot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.camelot.message.DingTalkMsgBuilderUtil;
import com.camelot.message.buildermsg.dto.RobotDingTalkMarkDownMsgDTO;
import com.camelot.message.enums.DingTalkMsgTypeEnum;
import com.camelot.robot.dao.RobotDao;
import com.camelot.robot.dao.RobotNoticeRecordDao;
import com.camelot.robot.model.MessageDTO;
import com.camelot.robot.model.RobotDO;
import com.camelot.robot.model.RobotNoticeRecordDO;
import com.camelot.robot.service.RobotService;
import com.dingtalk.api.request.OapiRobotSendRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <p>
 * Description:[]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
@Service
@Slf4j
public class RobotServiceImpl implements RobotService {
    @Autowired
    private RobotDao robotDao;
    @Autowired
    private RobotNoticeRecordDao robotNoticeRecordDao;

    private static final String MESSAGE_REG = "\\{[0-9]+\\}";

    @Override
    public OapiRobotSendRequest getRobotNoticesByKeyWord(MessageDTO messageDTO) {
        String preKeyWord = messageDTO.getText().getContent();
        if (StringUtils.isBlank(preKeyWord)) {
            OapiRobotSendRequest result = new OapiRobotSendRequest();
            result.setMsgtype("empty");
            log.error("请求参数为空");
            return result;
        }
        // 做一遍trim操作去除开头空格
        String keyWord = StringUtils.trimToEmpty(preKeyWord);
        log.info("关键字为：{}", keyWord);
        List<RobotDO> robotDOS = robotDao.selectList(
                Wrappers.<RobotDO>lambdaQuery().like(StringUtils.isNotBlank(keyWord), RobotDO::getKeyWord, keyWord));
        // 组装消息
        StringBuilder scontent = new StringBuilder();
        // 查到相应关键字
        if (CollectionUtils.isNotEmpty(robotDOS)) {
            for (int i = 0; i < robotDOS.size(); i++) {
                RobotDO robotDO = robotDOS.get(i);
                String content = robotDO.getContent();
                String title = robotDO.getTitle();
                String href = robotDO.getHref();

                scontent.append("### ").append(title);
                scontent.append("    ").append(" \n\n ");
                // 组装消息内容
                if (Pattern.matches(MESSAGE_REG, content) && StringUtils.isNotBlank(href)) {
                    String[] split = href.split(",");
                    if (split.length != 0) {
                        String format = MessageFormat.format(content, split);
                        scontent.append(format);
                    } else {
                        scontent.append(content);
                    }
                    scontent.append(" \n\n ").append("---").append(" \n\n ");
                } else {
                    scontent.append(content);
                    scontent.append(" \n\n ").append("---").append(" \n\n ");
                }
            }
        } else {
            // 未查到相应关键字
            scontent.append("#### 未查询到该关键字的相关内容。");
            scontent.append(" \n\n ").append("---").append(" \n\n ");
            List<RobotNoticeRecordDO> robotNoticeRecordList =
                    robotNoticeRecordDao.selectList(Wrappers.<RobotNoticeRecordDO>lambdaQuery().eq(RobotNoticeRecordDO::getKeyWord, keyWord));
            if (CollectionUtils.isNotEmpty(robotNoticeRecordList)) {
                for (RobotNoticeRecordDO doModel : robotNoticeRecordList) {
                    robotNoticeRecordDao.updateById(doModel);
                }
            }else {
                RobotNoticeRecordDO recordNoticeDO = new RobotNoticeRecordDO();
                recordNoticeDO.setKeyWord(keyWord);
                robotNoticeRecordDao.insert(recordNoticeDO);
            }
        }
        // 组装钉钉消息model
        return getMessageRequestModel(scontent);
    }

    private OapiRobotSendRequest getMessageRequestModel(StringBuilder scontent) {
        RobotDingTalkMarkDownMsgDTO robotDingTalkMarkDownMsgDTO = new RobotDingTalkMarkDownMsgDTO();
        robotDingTalkMarkDownMsgDTO.setText(scontent.toString());
        robotDingTalkMarkDownMsgDTO.setTitle("机器人回复");
        robotDingTalkMarkDownMsgDTO.setIsAtAll("false");
        robotDingTalkMarkDownMsgDTO.setMsgType(DingTalkMsgTypeEnum.MARKDOWN_MSG.getMsgType());
        return DingTalkMsgBuilderUtil.buildRobotMsgRequest(robotDingTalkMarkDownMsgDTO);
    }
}
