package com.lhj.controller;

import com.lhj.annotation.EnablePage;
import com.lhj.model.ResultInfo;
import com.lhj.model.dto.LoginDTO;
import com.lhj.model.entity.role.Role;
import com.lhj.service.inter.AuthService;
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
