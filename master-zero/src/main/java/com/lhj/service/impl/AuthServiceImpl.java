package com.lhj.service.impl;

import com.lhj.mapper.role.RoleMapper;
import com.lhj.mapper.user.UserMapper;
import com.lhj.model.entity.role.Role;
import com.lhj.service.inter.AuthService;
import com.lhj.utils.JwtUtil;
import com.lhj.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;


    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    @Value("${dictionaries.password}")
    String passwordDefault;
    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);//1

        return JwtUtil.createJWT(authentication);
    }

    @Override
    public void logout() {
        try {
            SecurityUtil.removeLoginUser();
        } catch (Exception ignored) {
        }
    }

    @Override
    public boolean register(String username, String password) {
        return userMapper.selectByUsername(username) == null
                && passwordDefault.equals(password);
    }

    @Override
    public List<Role> getRole() {
        if(SecurityUtil.getLoginUser() == null){
            return null;
        }
       return roleMapper.selectRolesByUserId(SecurityUtil.getLoginUser().getUserId());
    }
}
