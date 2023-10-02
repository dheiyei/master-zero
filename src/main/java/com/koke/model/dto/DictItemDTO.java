package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 字典序请求类
 * @author koke
 */
@Data
@ApiModel(description= "字典序请求对象")
public class DictItemDTO {
    private static final long serialVersionUID = 7663338382628446934L;

    @ApiModelProperty(value ="字典类型")
    private String dictType;

    @ApiModelProperty(value ="字典数据id",example = "0")
    private Long dictItemId;

    @ApiModelProperty(value ="字典id",example = "0")
    private Long dictId;

    @ApiModelProperty(value ="字典标签")
    private String dictLabel;

    @ApiModelProperty(value ="字典数据")
    private String dictValue;

    @ApiModelProperty(value ="备注")
    private String remark;

    @ApiModelProperty(value ="排序",example = "0")
    private Integer sort;

    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="修改时间")
    private LocalDateTime updateTime;
}
