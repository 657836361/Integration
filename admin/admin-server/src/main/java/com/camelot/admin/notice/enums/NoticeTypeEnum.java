package com.camelot.admin.notice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Admin
 * @date 2020/6/16
 */
@Getter
@AllArgsConstructor
public enum NoticeTypeEnum {
    /**
     * 钉钉工作通知
     */
    DING_TALK_WORK_NOTICE("dingTalkWorkNotice", "钉钉工作通知"),

    /**
     * 钉钉群通知
     */
    DING_TALK_GROUP_NOTICE("dingTalkGroupNotice", "钉钉群通知"),

    /**
     * 短信通知
     */
    SMS_NOTICE("smsNotice", "短信通知"),
    /**
     * 邮件通知
     */
    EMAIL_NOTICE("emailNotice", "邮件通知"),

    ;
    /**
     * code
     */
    private final String code;

    /**
     * 中文说明
     */
    private final String description;
}
