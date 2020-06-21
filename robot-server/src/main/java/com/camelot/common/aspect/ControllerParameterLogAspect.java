package com.camelot.common.aspect;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guanpei
 * @date 2020/6/2
 */
@Slf4j
@Aspect
@Component
public class ControllerParameterLogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointGet() {
        /**
         * aop method,do nothing
         */
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void post() {
        /**
         * aop method,do nothing
         */
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void put() {
        /**
         * aop method,do nothing
         */
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delete() {
        /**
         * aop method,do nothing
         */
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void request() {
        /**
         * aop method,do nothing
         */
    }

    @Around("pointGet()||post()||put()||delete()||request()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        Object[] args = pjp.getArgs();
        String param = jsonFormat(args);
        log.info("{} {} ---接口入参:[{}]", method.toUpperCase(), uri, param);
        Object proceed = pjp.proceed();

        String returns = jsonFormat(proceed);
        log.info("{} {} ---接口返回:[{}]", method.toUpperCase(), uri, returns);
        return proceed;
    }

    /**
     * 格式化参数
     *
     * @param params
     * @return
     */
    private String jsonFormat(Object... params) {
        if (ArrayUtils.isEmpty(params)) {
            return "";
        }
        return Arrays.stream(params).filter(this::consoleAble).map(JSON::toJSONString).collect(Collectors.joining(","));
    }

    /**
     * 过滤 不能序列化的参数
     *
     * @param param
     * @return
     */
    private boolean consoleAble(Object param) {
        if (param == null) {
            return false;
        }
        Class<?> clazz = param.getClass();
        String clazzName = clazz.getName();
        return !(Void.TYPE.equals(clazz) && !clazzName.startsWith("javax.servlet") && !clazzName.startsWith("org."));
    }
}
