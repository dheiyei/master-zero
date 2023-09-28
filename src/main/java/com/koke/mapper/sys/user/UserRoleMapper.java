package com.koke.mapper.sys.user;

import com.koke.model.entity.sys.user.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {

    List<UserRole> selectUserRoleByUserId(Long userId);

    void createUserRole(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    void deleteUserRoleByUserId(Long userId);

}
