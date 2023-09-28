package com.koke.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UpdatePasswordDTO implements Serializable {

    @NotBlank(message = "新密码不能为空")
    @Length(min=5, message = "密码长度不能少于5位")
    private String newPassword;

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

}
