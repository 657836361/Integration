package com.camelot.robot.model;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * 机器人关键字未查询到的数据记录表DO
 *
 * @author Admin
 * @date 2020/6/19
 */
@Data
public class RobotNoticeRecordDO implements Serializable {
    private static final long serialVersionUID = 2909263448772701162L;
    /**
     * 主键ID
     */
    private long id;
    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 乐观锁版本号，关键字被记录次数
     */
    @Version
    private Integer version;

}
