package com.camelot.admin.notice.notice.impl;

import com.camelot.admin.notice.notice.NoticeTypeFactory;
import com.camelot.admin.notice.notice.NoticeTypeService;
import com.camelot.admin.notice.enums.NoticeTypeEnum;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Admin
 * @date 2020/6/16
 */
public class DingTalkNoticeServiceImpl implements NoticeTypeService, InitializingBean {

    @Override
    public void sendMessage(String message) {

        System.out.println("发消息:" + message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        NoticeTypeFactory.register(NoticeTypeEnum.DING_TALK_WORK_NOTICE.getCode(), this);
    }
}
