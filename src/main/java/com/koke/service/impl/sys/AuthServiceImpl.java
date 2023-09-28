package com.koke.service.impl.sys;

import com.koke.mapper.sys.role.RoleMapper;
import com.koke.mapper.sys.user.UserMapper;
import com.koke.model.entity.sys.role.Role;
import com.koke.service.inter.sys.AuthService;
import com.koke.utils.JwtUtil;
import com.koke.utils.SecurityUtil;
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
        SecurityContextHolder.getContext().setAuthentication(authentication);

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
