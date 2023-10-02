package com.koke.model.entity.sys.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koke.model.entity.sys.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 角色类
 * @author koke
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description= "角色对象")
public class Role implements Serializable {

    private static final long serialVersionUID = 7801847299984757320L;

    @ApiModelProperty(value ="角色id",example = "0")
    private Long roleId;

    @ApiModelProperty(value ="角色标识")
    private String roleKey;

    @ApiModelProperty(value ="角色名")
    private String roleName;

    @ApiModelProperty(value ="角色描述")
    private String description;

    @ApiModelProperty(value ="数据范围")
    private String dataScope;

    @ApiModelProperty(value ="创建时间")
    private Date createTime;

    @ApiModelProperty(value ="修改时间")
    private Date updateTime;

    @JsonIgnore
    @ApiModelProperty(value ="关联菜单")
    private Set<Menu> menus;

}
