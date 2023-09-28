package com.koke.model.entity.sys.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koke.model.entity.sys.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 7801847299984757320L;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色标识
     */
    private String roleKey;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 数据范围
     */
    private String dataScope;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 关联菜单
     */
    @JsonIgnore
    private Set<Menu> menus;

}
