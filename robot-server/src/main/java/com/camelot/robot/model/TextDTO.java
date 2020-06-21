package com.camelot.robot.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author guanpei
 * @date 2020/6/18
 */
@Data
public class TextDTO implements Serializable {
    private static final long serialVersionUID = 583433674861577279L;
    /**
     * 消息文本
     */
    private String content;
}
