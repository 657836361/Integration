package com.camelot.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 
  * <p> 
  * Description:[监控服务]
  * </p> 
  * @author  shf
  * @version 1.0 
  * @date Created on  2020/6/17
  */
@EnableScheduling
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
@EnableAdminServer
public class MonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
		log.info("MonitorApplication start success");
	}

}
