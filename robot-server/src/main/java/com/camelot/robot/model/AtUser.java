package com.camelot.robot.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author guanpei
 * @date 2020/6/18
 */
@Data
public class AtUser implements Serializable {

    private static final long serialVersionUID = -753455049518795079L;

    // 加密的发送者ID
    private String dingtalkId;

    // 发送者在企业内的userid（企业内部群有）
    private String staffId;
}
