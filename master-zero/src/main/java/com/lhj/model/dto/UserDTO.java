package com.lhj.model.dto;

import com.lhj.model.entity.user.User;
import com.lhj.valid.CreateGroup;
import com.lhj.valid.UpdateGroup;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2388312016898120314L;


    /**
     * 用户id
     */
    @Null(message = "用户已存在", groups = CreateGroup.class)
    @NotNull(message = "用户不存在", groups = UpdateGroup.class)
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = CreateGroup.class)
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空", groups = CreateGroup.class)
    private String confirmPassword;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 是否可用
     */
    private boolean enabled = true;

    /**
     * 是否审批
     */
    private boolean approved = true;

    /**
     * 角色 id 列表
     */
    private List<Long> roleIds = new ArrayList<>();

    /**
     * 将 {@link User} 转化为 {@link UserDTO}
     */
    public static Function<User, UserDTO> fromUser = (user) -> UserDTO.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .password(user.getPassword())
            .nickname(user.getNickname())
            .enabled(user.isEnabled())
            .approved(user.isApproved())
            .build();

    public UserDTO(String username ,String passwordDefault) {
        this.username = username;
        this.password = passwordDefault;
        this.confirmPassword = passwordDefault;
        this.roleIds.add(2L);
    }
}
