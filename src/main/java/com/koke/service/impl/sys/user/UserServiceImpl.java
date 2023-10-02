package com.koke.service.impl.sys.user;

import com.koke.constant.Constants;
import com.koke.exception.CustomException;
import com.koke.mapper.sys.role.RoleMapper;
import com.koke.mapper.sys.user.UserMapper;
import com.koke.mapper.sys.user.UserRoleMapper;
import com.koke.model.dto.CacheUserDTO;
import com.koke.model.dto.UpdatePasswordDTO;
import com.koke.model.dto.UserDTO;
import com.koke.model.entity.sys.role.Role;
import com.koke.model.entity.sys.user.User;
import com.koke.service.inter.sys.user.UserRoleService;
import com.koke.service.inter.sys.user.UserService;
import com.koke.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final UserRoleMapper userRoleMapper;
    @Override
    public List<User> selectUsers(User user) {
        return userMapper.selectUsers(user);
    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Transactional
    @Override
    public void createUser(UserDTO userDTO) {
        Assert.isTrue(checkUsernameUnique(userDTO), Constants.USERNAME_EXISTED);
        userMapper.createUser(userDTO);
        //添加用户角色
        userRoleService.createUserRole(userDTO.getUserId(), userDTO.getRoleIds());
    }

    @Transactional
    @Override
    public void updateUser(UserDTO userDTO) {
        Assert.isTrue(checkUsernameUnique(userDTO), Constants.USERNAME_EXISTED);
        String password = userDTO.getPassword();
        if (StringUtils.isNotBlank(password)) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userMapper.updateUser(userDTO);
        //添加用户角色
        userRoleService.createUserRole(userDTO.getUserId(), userDTO.getRoleIds());
    }

    @Transactional
    @Override
    public void deleteUsersByIds(List<Long> userIds) {
        userMapper.deleteUsersByIds(userIds);
        //删除用户角色
        for (Long userId : userIds) {
            userRoleService.deleteUserRoleByUserId(userId);
        }
    }

    @Override
    public void updateUserRole(String key) {
        userRoleMapper.deleteUserRoleByUserId(SecurityUtil.getLoginUser().getUserId());
        List<Long> roleIds = new ArrayList<>();
        //添加用户角色
        Role role = roleMapper.selectByRoleKey(key);
        roleIds.add(role.getRoleId());
        userRoleMapper.createUserRole(SecurityUtil.getLoginUser().getUserId(), roleIds);
    }

    @Override
    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        CacheUserDTO loginUser = SecurityUtil.getLoginUser();
        Long userId = loginUser.getUserId();
        //查询用户
        User user = selectUserById(userId);
        if (user == null) {
            throw new CustomException("修改密码失败");
        }
        String dbPassword = user.getPassword();
        //比对新旧密码
        if (!passwordEncoder.matches(updatePasswordDTO.getOldPassword(), dbPassword)) {
            throw new CustomException("旧密码错误");
        }
        String password = passwordEncoder.encode(updatePasswordDTO.getNewPassword());
        userMapper.updatePassword(userId, password);
    }

    /**
     * 校验用户名是否唯一
     * @param userDTO
     * @return
     */
    private boolean checkUsernameUnique(UserDTO userDTO) {
        User user = userMapper.selectByUsername(userDTO.getUsername());
        if (user != null && !Objects.equals(user.getUserId(), userDTO.getUserId())) {
            return false;
        }
        return true;
    }

}
