package com.camelot.monitor.message.enums;

/**
 * <pre>
 * 异常返回码枚举接口
 * </pre>
 * @author Admin
 * @date 2020/6/18
 */
public interface IResponseEnum {

    /**
     * 获取返回码
     *
     * @return 返回码
     */
    int getCode();

    /**
     * 获取返回信息
     *
     * @return 返回信息
     */
    String getMessage();

    /**
     * 获取操作结果
     *
     * @return
     */
    String getSuccess();

}
