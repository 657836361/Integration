package com.camelot.monitor.message.buildermsg.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * Description:[工作链接消息]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public class WorkDingTalkLinkMsgDTO extends AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = -5567878373005776809L;

    /**
     * 消息标题，建议100字符以内
     */
    private String title;

    /**
     * 消息描述，建议500字符以内
     */
    private String text;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接
     */
    private String messageUrl;

    /**
     * 图片地址
     */
    private String picUrl;

}
