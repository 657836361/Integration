package com.camelot.robot.model;

import lombok.Data;

/**
 * <p>
 * Description:[页面展示对象]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2019/12/15
 */
@Data
public class RobotVO extends BaseVOModel {
    private static final long serialVersionUID = -3153049678246198056L;

    private String title;

    private String content;

}
