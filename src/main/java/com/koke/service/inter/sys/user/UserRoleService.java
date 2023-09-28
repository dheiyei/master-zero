package com.koke.service.inter.sys.user;

import com.koke.model.entity.sys.user.UserRole;

import java.util.List;

public interface UserRoleService {

    List<UserRole> selectUserRoleByUserId(Long userId);

    void createUserRole(Long userId, List<Long> roleIds);

    void deleteUserRoleByUserId(Long userId);

}
