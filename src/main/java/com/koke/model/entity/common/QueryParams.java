package com.koke.model.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页类
 * @author koke
 */
@Data
@ApiModel(description= "分页类对象")
public class QueryParams {

    @ApiModelProperty(value ="页数",example = "0")
    private int pageNum;

    @ApiModelProperty(value ="页大小",example = "0")
    private int pageSize;

}
