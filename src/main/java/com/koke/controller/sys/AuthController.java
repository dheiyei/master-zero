package com.koke.controller.sys;

import com.koke.aspect.annotation.EnablePage;
import com.koke.exception.WorkException;
import com.koke.model.dto.LoginDTO;
import com.koke.model.entity.common.ResultInfo;
import com.koke.model.entity.sys.role.Role;
import com.koke.service.inter.sys.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

/**
 * 用户登录管理控制层
 * @author koke
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Api(tags = "用户登录管理")
public class AuthController {

    private final AuthService authService;


    @Value("${dictionaries.password}")
    String passwordDefault;
    /**
     * 用户登录
     * @param loginDTO 登录请求对象
     * @return 令牌
     */
    @PostMapping("/login")
    @Transactional(rollbackFor = WorkException.class)
    @ApiOperation(value = "用户登录")
    public ResultInfo<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String jwt = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResultInfo.success(jwt);
    }

    /**
     * 用户退出
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出")
    public ResultInfo<Void> logout() {
        authService.logout();
        return ResultInfo.success();
    }

    @EnablePage
    @GetMapping("/thisRole")
    @ApiOperation(value = "获取当前权限")
    public ResultInfo<List<Role>>  getRole(){
        return ResultInfo.success( authService.getRole());
    }




}
