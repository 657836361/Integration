package com.camelot.admin.notice.notice;

/**
 * <p>
 * Description:消费者接口
 * </p>
 *
 * @author guanpei
 * @version 1.0
 * @date Created on 2020/4/3
 */
public interface NoticeTypeService {
    /**
     * 消费消息
     * 
     * @param message
     */
    void sendMessage(String message);
}