package com.lhj.service.inter.user;

import com.lhj.model.entity.user.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> selectUserRoleByUserId(Long userId);

    void createUserRole(Long userId, List<Long> roleIds);

    void deleteUserRoleByUserId(Long userId);

}
