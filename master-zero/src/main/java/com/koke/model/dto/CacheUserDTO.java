package com.koke.model.dto;

import com.koke.model.UserPrincipal;
import com.koke.model.entity.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CacheUserDTO implements Serializable {

    private static final long serialVersionUID = -7498232750103952522L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色列表
     */
    private List<Role> roles;

    /**
     * 权限列表
     */
    private Set<String> authorities;


    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 将 {@link UserPrincipal} 转化为 {@link CacheUserDTO}
     */
    public static Function<UserPrincipal, CacheUserDTO> fromUserPrincipal = (user) -> CacheUserDTO.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .nickname(user.getNickname())
            .enabled(user.isEnabled())
            .roles(user.getRoles())
            .authorities(user.getAuthorities().stream().map(String::valueOf).collect(Collectors.toSet()))
            .build();

}
