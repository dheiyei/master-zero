package com.koke.controller;

import com.koke.annotation.EnablePage;
import com.koke.model.ResultInfo;
import com.koke.model.dto.LoginDTO;
import com.koke.model.entity.role.Role;
import com.koke.service.inter.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    @Value("${dictionaries.password}")
    String passwordDefault;
    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    @Transactional
    public ResultInfo<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String jwt = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResultInfo.success(jwt);
    }

    /**
     * 用户退出
     *
     * @return
     */
    @PostMapping("/logout")
    public ResultInfo<Void> logout() {
        authService.logout();
        return ResultInfo.success();
    }
    @EnablePage
    @GetMapping("/thisRole")
    public ResultInfo<List<Role>>  getRole(){
        return ResultInfo.success( authService.getRole());
    }




}
