package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录请求类
 * @author koke
 */
@Data
@ApiModel(description= "登录请求对象")
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -1853694108459583032L;

    @NotBlank(message = "请输入用户名")
    @ApiModelProperty(value ="账号")
    private String username;

    @NotBlank(message = "请输入密码")
    @ApiModelProperty(value ="密码")
    private String password;

//    @ApiModelProperty(value ="是否")
//    private Boolean rememberMe = false;
}
