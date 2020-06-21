package com.camelot.monitor.task;

import com.camelot.monitor.utils.MonitorServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * <p>
 * Description:[服务器监控定时任务]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on  2020/6/17 13:38
 */
@Configuration
@Slf4j
public class MonitorServerTask {

    @Autowired
    private MonitorServerUtil monitorServerUtil;

    /**
     * 功能描述: <br>
     * 〈每五小时查询一次服务器状态〉
     * @Author: shihengfei
     * @Date: 2020/6/17 18:47
     */
    @Scheduled(cron="0 0 0/5 * * ? ")
    public void serverTask(){
        log.info("监控服务器运行状态定时任务--------->start");
        monitorServerUtil.exec();
        log.info("监控服务器运行状态定时任务--------->end");
    }
}
