package com.camelot.admin.notice.server.impl;

import com.camelot.admin.notice.enums.InstanceTypeEnum;
import com.camelot.admin.notice.server.ServerInstanceFactory;
import com.camelot.admin.notice.server.ServerInstanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author Admin
 * @date 2020/6/16
 */
@Service
@Slf4j
public class ServerDownServiceImpl implements ServerInstanceService, InitializingBean {

    @Override
    public void execute(String serverName, String serverStatus) {
        log.info("发送 健康检查没通过 的通知！");
        System.out.println(serverName+"服务下线了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerInstanceFactory.register(InstanceTypeEnum.DOWN.getType(),this);
    }
}
