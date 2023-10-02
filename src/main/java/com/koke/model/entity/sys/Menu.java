package com.koke.model.entity.sys;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单类
 * @author koke
 */
@Data
@ApiModel("菜单对象")
public class Menu implements Serializable {

    private static final long serialVersionUID = -8217256912346829217L;

    @ApiModelProperty(value ="菜单id",example = "0")
    private Long menuId;

    @NotBlank(message = "单位名称不能为空")
    @ApiModelProperty(value ="菜单名称")
    private String menuName;

    @ApiModelProperty(value ="父菜单id",example = "0")
    private Long parentId;

    @ApiModelProperty(value ="菜单url")
    private String url;

    @ApiModelProperty(value ="权限标识")
    private String permission;

    @ApiModelProperty(value ="类型 0：目录 1：菜单 2：按钮")
    private String type;

    @ApiModelProperty(value ="排序",example = "0")
    private Integer sort;

    @ApiModelProperty(value ="图标")
    private String icon;

    @ApiModelProperty(value ="删除状态 0：存在 1：删除")
    private String delFlag;

    @ApiModelProperty(value ="创建时间")
    private Date createTime;

    @ApiModelProperty(value ="修改时间")
    private Date updateTime;

}
