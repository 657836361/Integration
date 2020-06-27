package com.camelot.admin.notice.server;

/**
 * <p>
 * Description:消费者接口
 * </p>
 *
 * @author guanpei
 * @version 1.0
 * @date Created on 2020/4/3
 */
public interface ServerInstanceService {
    /**
     * 执行相应任务
     *
     * @param serverName 服务名称
     * @param serverStatus 服务现在的状态
     */
    void execute(String serverName, String serverStatus);
}