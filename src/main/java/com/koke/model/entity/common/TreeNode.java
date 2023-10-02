package com.koke.model.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 树形节点属性对象
 * @author koke
 */
@Data
@ApiModel(description= "树形节点属性类")
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -6001536163407217031L;

    @ApiModelProperty(value ="节点id",example = "0")
    private Long id;

    @ApiModelProperty(value ="节点名称")
    private String name;

    @ApiModelProperty(value ="父节点id",example = "0")
    private Long parentId;

    @ApiModelProperty(value ="排序",example = "0")
    private Integer sort;

    @ApiModelProperty(value ="扩展字段")
    private Map<String, Object> extra;

}
