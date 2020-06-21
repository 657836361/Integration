package com.camelot.robot.model;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * Description:[应用间数据传输对象]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
@Data
public class RobotDTO implements Serializable {

    private static final long serialVersionUID = 6360240553552028108L;
    /**
     * 字段
     */
    private String keyWord;

    /**
     * 当前@ 的用户
     */
    private String user;

    /**
     * 备注
     */
    private String remark;
}
