package com.koke.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 更新密码请求类
 * @author koke
 */
@Data
@ApiModel(description= "更新密码请求对象")
public class UpdatePasswordDTO implements Serializable {

    @NotBlank(message = "新密码不能为空")
    @Length(min=5, message = "密码长度不能少于5位")
    @ApiModelProperty(value ="新密码")
    private String newPassword;

    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty(value ="旧密码")
    private String oldPassword;

}
