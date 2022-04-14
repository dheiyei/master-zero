package com.lhj.controller;

import com.lhj.annotation.EnablePage;
import com.lhj.constant.CommonConstants;
import com.lhj.model.ResultInfo;
import com.lhj.model.dto.CacheUserDTO;
import com.lhj.model.dto.UpdatePasswordDTO;
import com.lhj.model.dto.UserDTO;
import com.lhj.model.entity.user.User;
import com.lhj.model.entity.user.UserRole;
import com.lhj.model.vo.UserVO;
import com.lhj.service.inter.user.UserRoleService;
import com.lhj.service.inter.user.UserService;
import com.lhj.utils.SecurityUtil;
import com.lhj.valid.CreateGroup;
import com.lhj.valid.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user")
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @EnablePage
    @PreAuthorize("hasAnyAuthority('admin:user:read')")
    @GetMapping
    public ResultInfo<List<User>> getUsers(User user) {
        List<User> users = userService.selectUsers(user);
        return ResultInfo.success(users);
    }

    @PreAuthorize("hasAnyAuthority('admin:user:read')")
    @GetMapping("/{userId}")
    public ResultInfo<UserVO> getUserById(@PathVariable("userId") Long userId) {
        List<Long> roleIds = userRoleService.selectUserRoleByUserId(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        UserVO userVO = Optional.ofNullable(userService.selectUserById(userId))
                .map(user -> Pair.of(UserVO.fromUser.apply(user), roleIds)).map(pair -> pair.getLeft().withRoleIds(pair.getRight()))
                .orElse(null);
        return ResultInfo.success(userVO);
    }

    @PreAuthorize("hasAnyAuthority('admin:user:create')")
    @PostMapping
    public ResultInfo<Void> createUser(@Validated(CreateGroup.class) @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResultInfo.success();
    }

    //todo:需要完善修改用户缓存清除
    @CacheEvict(value = CommonConstants.CACHE_PREFIX_ADMIN_USER_MENU, allEntries = true)
    @PutMapping
    public ResultInfo<Void> updateUser(@Validated(UpdateGroup.class) @RequestBody UserDTO   userDTO) {
        userService.updateUser(userDTO);
        return ResultInfo.success();
    }

    /**
     * 个人中心--修改密码
     *
     * @param updatePasswordDTO
     * @return
     */
    @PutMapping("/updatePassword")
    public ResultInfo<Void> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return ResultInfo.success();
    }

    @DeleteMapping("/{userIds}")
    public ResultInfo<Void> deleteUsersByIds(@PathVariable("userIds") List<Long> userIds) {
        userService.deleteUsersByIds(userIds);
        return ResultInfo.success();
    }

    @PutMapping("/userRole/{key}")
    public ResultInfo<Void> updateUserRole(@PathVariable("key") String key) {
        userService.updateUserRole(key);
        return ResultInfo.success();
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResultInfo<CacheUserDTO> getUserInfo() {
        CacheUserDTO loginUser = SecurityUtil.getLoginUser();
        return ResultInfo.success(loginUser);
    }

//    /**
//     * 审批用户
//     *
//     * @param userApproveDTO
//     * @return
//     */
//    @PostMapping("/approve")
//    public ResultInfo<Void> approveUsers(@RequestBody UserApproveDTO userApproveDTO) {
//        userService.approveUsers(userApproveDTO);
//        return ResultInfo.success();
//    }

}
