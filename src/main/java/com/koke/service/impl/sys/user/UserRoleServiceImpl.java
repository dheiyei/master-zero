package com.koke.service.impl.sys.user;

import com.koke.mapper.sys.user.UserRoleMapper;
import com.koke.model.entity.sys.user.UserRole;
import com.koke.service.inter.sys.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> selectUserRoleByUserId(Long userId) {
        return userRoleMapper.selectUserRoleByUserId(userId);
    }

    @Transactional
    @Override
    public void createUserRole(Long userId, List<Long> roleIds) {
        //删除用户角色
        deleteUserRoleByUserId(userId);
        //添加用户角色
        if (!CollectionUtils.isEmpty(roleIds) && userId != null) {
            userRoleMapper.createUserRole(userId, roleIds);
        }
    }

    @Transactional
    @Override
    public void deleteUserRoleByUserId(Long userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
    }

}
