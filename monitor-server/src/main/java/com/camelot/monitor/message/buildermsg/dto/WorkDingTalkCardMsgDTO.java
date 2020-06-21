package com.camelot.monitor.message.buildermsg.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Description:[工作卡片消息]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public class WorkDingTalkCardMsgDTO extends AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = -5247952798034790740L;

    /**
     * 透出到会话列表和通知的文案，最长64个字符
     */
    private String title;

    /**
     * 消息内容，支持markdown，语法参考标准markdown语法。建议1000个字符以内
     */
    private String markdown;

    /**
     * 使用整体跳转ActionCard样式时的标题，必须与single_url同时设置，最长20个字符
     */
    private String singleTitle;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接，最长500个字符
     */
    private String singleUrl;
}
