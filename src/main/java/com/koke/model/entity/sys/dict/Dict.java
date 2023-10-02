package com.koke.model.entity.sys.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 字典类
 * @author koke
 */
@Data
@ApiModel(description= "字典对象")
public class Dict implements Serializable {

    private static final long serialVersionUID = 5722615886340377469L;

    @ApiModelProperty(value ="唯一标识",example = "0")
    private Long dictId;

    @ApiModelProperty(value ="字典类型")
    @NotBlank(message = "字典类型不能为空")
    private String dictType;

    @ApiModelProperty(value ="字典名称")
    @NotBlank(message = "字典名称不能为空")
    private String dictName;

    @ApiModelProperty(value ="备注")
    private String remark;

    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="修改时间")
    private LocalDateTime updateTime;

}
