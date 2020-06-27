package com.camelot.admin.notice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * <p>
 * Description:[业务类型]
 * </p>
 *
 * @author qiaoyongle
 * @version 1.0
 * @date Created on 2020/4/3
 */
@Getter
@AllArgsConstructor
public enum InstanceTypeEnum {

    /**
     * 健康检查没通过
     */
    DOWN("DOWN","健康检查没通过"),

    /**
     * 服务离线
     */
    OFFLINE("OFFLINE","服务离线"),

    /**
     * 服务上线
     */
    UP("UP","服务上线"),

    /**
     * 服务未知异常
     */
    UNKNOWN("UNKNOWN","服务未知异常"),

    ;

    /**
     * 业务类型
     */
    private String type;

    private String description;

    /**
     * 获取type 对应的枚举
     * 
     * @param type
     * @return
     */
    public static InstanceTypeEnum getEnumByType(String type) {
        InstanceTypeEnum[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(type, values[i].getType())) {
                return values[i];
            }
        }
        return null;
    }
}
