package com.lhj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lhj.model.entity.role.Role;
import com.lhj.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserPrincipal implements UserDetails {

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
     * 角色列表
     */
    private List<Role> roles;

    /**
     * 权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 是否可用
     */
    private boolean enabled;

    /**
     * 数据权限类型
     */
    private String dataScopeType;

    /**
     * 数据权限值
     */
    private List<Long> dataScopeValues;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 是否过期
     *
     * @return 未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否锁定
     *
     * @return 未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     *
     * @return 未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 将 {@link User} 转化为 {@link UserPrincipal}
     */
    public static Function<User, UserPrincipal> fromUser = (user) -> {
        List<Role> roles = user.getRoles();
        return UserPrincipal.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .enabled(user.isEnabled())
                .roles(roles)
                .authorities(roles.stream().flatMap(role -> role.getMenus().stream()).filter(menu -> StringUtils.isNotBlank(menu.getPermission())).map(menu -> new SimpleGrantedAuthority(menu.getPermission())).collect(Collectors.toSet()))
                .build();
    };

}
