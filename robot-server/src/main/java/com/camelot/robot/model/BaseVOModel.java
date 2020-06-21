package com.camelot.robot.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Title: BaseCreateModel
 * </p>
 * <p>
 * Description: [model仅包含新增的字段基类]
 * </p>
 */
@Data
public class BaseVOModel implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", notes = "创建时间")
    private Date gmtCreate;

    /**
     * 创建人
     */
    @JsonIgnore
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人", notes = "创建人")
    private String createUser;
}
