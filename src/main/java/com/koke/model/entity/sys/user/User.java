package com.koke.model.entity.sys.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koke.model.entity.sys.role.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户信息类
 * @author koke
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("用户信息对象")
public class User implements Serializable {

    private static final long serialVersionUID = 6771759760856309745L;

    @ApiModelProperty(value ="唯一数据",example = "1")
    private Long userId;

    @ApiModelProperty(value ="用户名")
    private String username;

    @JsonIgnore
    @ApiModelProperty(value ="密码")
    private String password;

    @ApiModelProperty(value ="昵称")
    private String nickname;

    @ApiModelProperty(value ="是否可用")
    private boolean enabled;

    @ApiModelProperty(value ="是否审批")
    private boolean approved;

    @ApiModelProperty(value ="创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value ="修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value ="关联角色")
    private List<Role> roles;

    public User(String username) {
        this.username = username;
    }
}
