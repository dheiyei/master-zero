package com.koke.model.dto;

import com.koke.model.entity.sys.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 用户请求类
 * @author koke
 */
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Data
@ApiModel(description= "用户请求对象")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -2388312016898120314L;

    @Null(message = "用户已存在")
    @NotNull(message = "用户不存在")
    @ApiModelProperty(value ="唯一ID",example = "0")
    private Long userId;

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value ="用户名称")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value ="密码")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    @ApiModelProperty(value ="确认密码")
    private String confirmPassword;

    @ApiModelProperty(value ="昵称")
    private String nickname;

    @ApiModelProperty(value ="是否可用")
    private boolean enabled = true;

    @ApiModelProperty(value ="是否审批")
    private boolean approved = true;

    @ApiModelProperty(value ="角色id列表")
    private List<Long> roleIds = new ArrayList<>();

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
