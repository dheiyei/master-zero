package com.koke.model.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.koke.model.entity.sys.role.Role;
import com.koke.model.entity.sys.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

/**
 * 当前用户对象
 * @author koke
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ApiModel(description= "当前用户类")
public class UserPrincipal implements UserDetails {

    @ApiModelProperty(value ="唯一标识",example = "0")
    private Long userId;

    @ApiModelProperty(value ="用户名")
    private String username;

    @JsonIgnore
    @ApiModelProperty(value ="密码")
    private String password;

    @ApiModelProperty(value ="昵称")
    private String nickname;

    @ApiModelProperty(value ="角色列表")
    private List<Role> roles;

    @ApiModelProperty(value ="权限列表")
    private Collection<? extends GrantedAuthority> authorities;

    @ApiModelProperty(value ="是否可用")
    private boolean enabled;

    @ApiModelProperty(value ="数据权限类型")
    private String dataScopeType;

    @ApiModelProperty(value ="数据权限值")
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
