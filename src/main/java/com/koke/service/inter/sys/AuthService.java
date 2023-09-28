package com.koke.service.inter.sys;

import com.koke.model.entity.sys.role.Role;

import java.util.List;

public interface AuthService {

    String login(String username, String password);

    void logout();

    boolean register(String username , String password);

    List<Role> getRole();
}
