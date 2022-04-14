package com.lhj.service.inter;

import com.lhj.model.entity.role.Role;

import java.util.List;

public interface AuthService {

    String login(String username, String password);

    void logout();

    boolean register(String username , String password);

    List<Role> getRole();
}
