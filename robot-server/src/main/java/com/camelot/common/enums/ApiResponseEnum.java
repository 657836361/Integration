package com.camelot.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description: 操作是否成功标识枚举<br/>
 * Created on: 2019/10/10<br/>
 *
 * @author: <a href="mailto: zhangshanming76@camelotchina.com">张山明</a><br/>
 * @version: 1.0 <br/>
 *           Copyright (c) 2017年 西安柯莱特科技有限公司 交付部
 */

@AllArgsConstructor
@Getter
public enum ApiResponseEnum {

    Y("Y", "操作成功"),

    N("N", "操作失败");

    /**
     * code值
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
}
