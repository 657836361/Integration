package com.camelot.admin.notice.notice;


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
public class NoticeTypeFactory {

    /**
     * 存储消息消费服务
     */
    private static Map<String, NoticeTypeService> noticeServices = new ConcurrentHashMap<>();

    /**
     * 服务注册
     *
     * @param service
     */
    public static void register(String bizType, NoticeTypeService service) {
        noticeServices.put(bizType, service);
    }

    /**
     * 获取消费处理类
     *
     * @return
     */
    public static NoticeTypeService getConsumerService(String bizType) {
        return noticeServices.get(bizType);
    }

}
