package com.camelot.robot.model;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 * Description:[面向数据库的对象]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
@Data
@TableName("robot_business_notice")
public class RobotDO extends BaseModel {

    private static final long serialVersionUID = -3592613770518983324L;
    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息主要内容
     */
    private String content;

    /**
     * 超链接
     */
    private String href;
}
