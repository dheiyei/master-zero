package com.koke.model.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 树形节点配置对象
 * @author koke
 */
@Data
@ApiModel(description= "树形节点配置类")
public class TreeNodeConfig implements Serializable {

    private static final long serialVersionUID = 6024148312895263909L;

    @ApiModelProperty(value ="树形节点配置类")
    public static TreeNodeConfig DEFAULT_CONFIG = new TreeNodeConfig();

    @ApiModelProperty(value ="唯一标识")
    private String id = "id";

    @ApiModelProperty(value ="名称")
    private String name = "name";

    @ApiModelProperty(value ="父节点id")
    private String parentId = "parentId";

    @ApiModelProperty(value ="排序")
    private String sort = "sort";

    @ApiModelProperty(value ="其他")
    private String children = "children";

}
