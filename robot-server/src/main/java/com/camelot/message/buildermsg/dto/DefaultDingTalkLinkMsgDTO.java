package com.camelot.message.buildermsg.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[普通 link 消息]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/3/16 18:14
 */
@Data
@Accessors(chain = true)
public class DefaultDingTalkLinkMsgDTO extends AbstractDefaultDingTalkMsgDTO {

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
