package com.koke.model.entity.sys.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典项类
 * @author koke
 */
@Data
@ApiModel(description= "字典项对象")
public class DictItem implements Serializable {

    private static final long serialVersionUID = 7663338382628446934L;

    /**
     * 字典数据id
     */
    @ApiModelProperty(value ="唯一标识",example = "0")
    private Long dictItemId;

    @NotNull(message = "请选择字典")
    @ApiModelProperty(value ="字典id",example = "0")
    private Long dictId;

    @NotBlank(message = "字典标签不能为空")
    @ApiModelProperty(value ="字典标签")
    private String dictLabel;

    @NotBlank(message = "字典数据不能为空")
    @ApiModelProperty(value ="字典数据")
    private String dictValue;

    @ApiModelProperty(value ="备注")
    private String remark;

    @ApiModelProperty(value ="排序")
    private Integer sort;

    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="修改时间")
    private LocalDateTime updateTime;

}
