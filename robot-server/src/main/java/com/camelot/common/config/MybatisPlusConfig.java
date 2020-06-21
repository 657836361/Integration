package com.camelot.common.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/**
 * <p>
 * Title: MybatisPlusConfig
 * </p>
 * <p>
 * Description: [mybatisPlus 配置]
 * </p>
 */

@Configuration
@MapperScan("com.*.dao")
public class MybatisPlusConfig {

    @Value("${mybatis-plus.writeInLog}")
    private boolean writeInLog;

    @Bean
    public ISqlInjector getMetaObjectHandler() {
        return new LogicSqlInjector();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor().setFormat(true).setWriteInLog(writeInLog);
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
