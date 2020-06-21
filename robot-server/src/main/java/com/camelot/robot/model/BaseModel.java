package com.camelot.robot.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * <p>
 * Title: BaseModel
 * </p>
 * <p>
 * Description: [model公共字段基类]
 * </p>
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据主键
     */
    @TableId(type = IdType.AUTO)
    @JsonIgnore
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JsonIgnore
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonIgnore
    @TableField(value = "gmt_modify", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 修改人
     */
    @JsonIgnore
    @TableField(value = "modify_user", fill = FieldFill.INSERT_UPDATE)
    private String modifyUser;

}
