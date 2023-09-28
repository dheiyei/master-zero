package com.koke.model.entity.sys.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.koke.model.entity.sys.role.Role;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6771759760856309745L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 是否审批
     */
    private boolean approved;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 关联角色
     */
    private List<Role> roles;



    public User(String username) {
        this.username = username;
    }
}
