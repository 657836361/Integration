package com.camelot.admin.notice.server;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Description:[任务服务工厂]
 * </p>
 *
 * @author yanzhong.li
 * @version 1.0
 * @date Created on 2020/3/15
 */
public class ServerInstanceFactory {

    /**
     * 存储消息消费服务
     */
    private static Map<String, ServerInstanceService> SERVER_STATUS_SERVICES = new ConcurrentHashMap<>();

    /**
     * 服务注册
     *
     * @param service
     */
    public static void register(String bizType, ServerInstanceService service) {
        SERVER_STATUS_SERVICES.put(bizType, service);
    }

    /**
     * 获取消费处理类
     *
     * @return
     */
    public static ServerInstanceService getConsumerService(String bizType) {
        return SERVER_STATUS_SERVICES.get(bizType);
    }

}
