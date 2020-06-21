package com.camelot.message.buildermsg.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * Description:[工作消息公共部分]
 * </p>
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/4
 */
@Data
@Accessors(chain = true)
public abstract class AbstractWorkDingTalkMsgDTO implements Serializable {

    private static final long serialVersionUID = 4206371812738211576L;

    /**
     * 消息类型 必填
     */
    private String msgType;

    /***
     * agentId 必填
     */
    private Long agentId;

    /**
     * 是否发送给企业全部用户(userIdList,deptIdList, toAllUser 必须有一个不能为空)
     */
    private Boolean toAllUser;

    /**
     * 接收者的企业内部用户的userid列表，最大用户列表长度：100
     */
    private String userIdList;

    /**
     * 接收者的部门id列表，最大列表长度：20, 接收者是部门id下(包括子部门下)的所有用户
     */
    private String deptIdList;
}
