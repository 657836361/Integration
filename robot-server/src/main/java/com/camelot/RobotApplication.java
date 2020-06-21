package com.camelot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * Description:[spring boot 启动类]
 * </p>
 * 
 * @date Created on 20191215
 * @author yanzhong.li
 * @version 1.0
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.camelot.*.dao")
public class RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
        log.info("RobotApplication start success");
    }

}
