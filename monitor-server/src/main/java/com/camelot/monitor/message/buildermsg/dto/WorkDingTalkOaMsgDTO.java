package com.camelot.monitor.message.buildermsg.dto;

import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Description:[工作OA消息]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4 11:47
 */
@Data
@Accessors(chain = true)
public class WorkDingTalkOaMsgDTO extends AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = 6900963768031043531L;

    /**
     * 消息点击链接地址，当发送消息为小程序时支持小程序跳转链接
     */
    private String messageUrl;

    /**
     * PC端点击消息时跳转到的地址
     */
    private String pcMessageUrl;

    /**
     * 消息头部的背景颜色。长度限制为8个英文字符，其中前2为表示透明度，后6位表示颜色值。不要添加0x
     */
    private String bgColor;

    /**
     * 消息的头部标题 (向普通会话发送时有效，向企业会话发送时会被替换为微应用的名字)，长度限制为最多10个字符
     */
    private String text;

    /**
     * 消息体的标题，建议50个字符以内
     */
    private String title;

    /**
     * 消息体的表单，最多显示6个，超过会被隐藏
     */
    private List<OapiMessageCorpconversationAsyncsendV2Request.Form> form;

    /**
     * 单行富文本信息
     */
    private OapiMessageCorpconversationAsyncsendV2Request.Rich rich;

    /**
     * 消息体的内容，最多显示3行
     */
    private String content;

    /**
     * 消息体中的图片，支持图片资源@mediaId
     */
    private String image;

    /**
     * 自定义的附件数目。此数字仅供显示，钉钉不作验证
     */
    private String fileCount;

    /**
     * 自定义的作者名字
     */
    private String author;

}
