package com.koke.model.dto;

import com.koke.model.entity.common.UserPrincipal;
import com.koke.model.entity.sys.role.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 校验用户请求类
 * @author koke
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ApiModel(description= "校验用户请求对象")
public class CacheUserDTO implements Serializable {

    private static final long serialVersionUID = -7498232750103952522L;

    @ApiModelProperty(value ="用户id",example = "0")
    private Long userId;

    @ApiModelProperty(value ="用户名")
    private String username;

    @ApiModelProperty(value ="昵称")
    private String nickname;

    @ApiModelProperty(value ="角色列表")
    private List<Role> roles;

    @ApiModelProperty(value ="权限列表")
    private Set<String> authorities;

    @ApiModelProperty(value ="是否可用")
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
