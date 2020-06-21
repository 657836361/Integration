package com.camelot.monitor.utils;


import com.camelot.monitor.message.DingTalkMsgBuilderUtil;
import com.camelot.monitor.message.buildermsg.dto.RobotDingTalkTextMsgDTO;
import com.camelot.monitor.message.dingtalk.DingTalkUtil;
import com.camelot.monitor.message.enums.DingTalkMsgTypeEnum;
import com.dingtalk.api.request.OapiRobotSendRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * Description:[服务器监控]
 * </p>
 *
 * @author shf
 * @version 1.0
 * @date Created on  2020/6/15 14:16
 */
@Component
@Slf4j
public class MonitorServerUtil {

    @Value("${monitorServer.hosts}")
    private String HOSTS;

    @Value("${monitorServer.webhook}")
    private String WEBHOOK;

    @Value("${monitorServer.secret}")
    private String SECRET;

    /** 获取服务器内存脚本 */
    private static final String GET_MEMUSAGE = " free | grep Mem | awk '{print $2, $4, $6}'";

    /** 获取服务器磁盘脚本 */
    private static final String GET_DISK_USAGE = " df -h|grep -v Filesystem | grep /home ";

    /** 命令前缀 */
    private static final String PREFIX = "ssh ";

    /** 阈值 */
    private static final Integer THRESHOLD = 90;

    /**
     * 功能描述: <br>
     * 〈运行shell并获得结果〉
     * @Author: shihengfei
     * @Date: 2020/6/15 14:24
     */
    public static List<String> runShell(String shStr) {
        List<String> strList = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            process.waitFor();
            while ((line = input.readLine()) != null){
                strList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;
    }

    /**
     * 功能描述: <br>
     * 〈获取内存使用率〉
     * @Date: 2020/6/15 17:18
     */
    public static Integer getMemUsage(String menShell){
        List<String> free = runShell(menShell);
        String mem = free.get(0);
        String[] mems = mem.split(" ");
        Double totalMem = Double.parseDouble(mems[0]);
        log.info("总内存:{}M",totalMem/1024);
        // 空闲内存+缓存区内存 buff/cache
        Double freeMem = Double.parseDouble(mems[1])+Double.parseDouble(mems[2]);
        log.info("可用内存:{}M",freeMem/1024);
        Double memUsage = (freeMem*100)/totalMem;
        return memUsage.intValue();
    }

    /**
     * 功能描述: <br>
     * 〈获取磁盘使用情况〉
     * @Date: 2020/6/15 17:22
     */
    public static Integer getDiskUsage(String diskShell){
        List<String> result = runShell(diskShell);
        String[] disk = result.get(0).split(" ");
        List<String> collect = Arrays.stream(disk).filter(item -> item.contains("%")).collect(Collectors.toList());
        return Integer.parseInt(collect.get(0).replace("%", ""));
    }

    /**
     * 功能描述: <br>
     * 〈监控服务器状态〉
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/17 11:48
     */
    public void exec(){
        String[] hosts = HOSTS.split(",");
        // 读配置文件 ip地址列表
        List<String> hostList = Arrays.asList(hosts);
        // 遍历执行
        hostList.forEach(host -> {
            log.info("查询服务器:{}",host);
            // 获取内存状态
            Integer memUsage = getMemUsage(PREFIX + host + GET_MEMUSAGE);
            log.info("服务器内存:{}%",memUsage);
            // 如果大于90，发送通知消息
            if (memUsage > THRESHOLD) {
                String content = "警告!服务器【"+host+"】内存已使用:"+memUsage+"%";
                sendMsg(content);
            }
            // 获取磁盘状态
            Integer diskUsage = getDiskUsage(PREFIX + host + GET_DISK_USAGE);
            log.info("服务器磁盘占用:{}%",diskUsage);
            // 如果大于 90，发送通知消息
            if (diskUsage > THRESHOLD) {
                String content = "警告!服务器【"+host+"】磁盘空间已使用:"+diskUsage+"%";
                sendMsg(content);
            }
        });
    }

    /***
     * 发送消息
     * @param content
     */
    public void sendMsg(String content){
        RobotDingTalkTextMsgDTO robotDingTalkTextMsgDTO = new RobotDingTalkTextMsgDTO();
        robotDingTalkTextMsgDTO.setContent(content);
        robotDingTalkTextMsgDTO.setMsgType(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        OapiRobotSendRequest oapiRobotSendRequest = DingTalkMsgBuilderUtil.buildRobotMsgRequest(robotDingTalkTextMsgDTO);
        DingTalkUtil.sendRobotMsg(oapiRobotSendRequest,SECRET,WEBHOOK);
    }
}
