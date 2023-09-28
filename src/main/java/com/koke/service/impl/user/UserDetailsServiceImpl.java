package com.koke.service.impl.user;

import com.koke.constant.CommonConstants;
import com.koke.constant.Status;
import com.koke.mapper.MenuMapper;
import com.koke.mapper.role.RoleMapper;
import com.koke.mapper.user.UserMapper;
import com.koke.model.UserPrincipal;
import com.koke.model.entity.Menu;
import com.koke.model.entity.role.Role;
import com.koke.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userMapper.selectByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(Status.USERNAME_PASSWORD_ERROR.getMessage()));
        List<Role> roles = roleMapper.selectRolesByUserId(user.getUserId());
        user.setRoles(roles);
        UserPrincipal userPrincipal = userToUserPrincipal(user);
        return userPrincipal;
    }

    /**
     * 将 {@link User} 转化为 {@link UserPrincipal}
     *
     * @param user
     * @return
     */
    private UserPrincipal userToUserPrincipal(User user) {
        List<Menu> menus = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (CommonConstants.ROLE_ADMIN.equals(role.getRoleKey())) {
                menus = menuMapper.selectMenus(null);
                break;
            } else {
                menus = menuMapper.selectMenusByUserId(user.getUserId());
            }
        }
        UserPrincipal userPrincipal = UserPrincipal.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .enabled(user.isEnabled())
                .roles(roles)
                .authorities(menus.stream().filter(menu -> StringUtils.isNotBlank(menu.getPermission())).map(menu -> new SimpleGrantedAuthority(menu.getPermission())).collect(Collectors.toSet()))
                .build();
        return userPrincipal;
    }

//    /**
//     * 给登录用户设置数据权限信息
//     *
//     * @param user
//     * @return
//     */
//    private UserPrincipal setUserPrincipalDataScope(UserPrincipal user) {
//        Long unitId = user.getUnitId();
//        List<Role> roles = user.getRoles();
//        for (Role role : roles) {
//            String roleDataScope = role.getDataScope();
//            if (DataScopeType.ALL.getValue().equals(roleDataScope)) {
//                user.setDataScopeType(DataScopeType.ALL.getValue());
//                break;
//            } else if (DataScopeType.CUSTOM.getValue().equals(roleDataScope)) {
//                List<RoleUnit> roleUnits = roleUnitService.selectRoleUnitByRoleId(role.getRoleId());
//                if (CollectionUtils.isEmpty(roleUnits)) {
//                    //角色单位为空，只能查询自己
//                    user.setDataScopeType(DataScopeType.SELF.getValue());
//                } else {
//                    List<Long> unitIds = roleUnits.stream().map(RoleUnit::getUnitId).collect(Collectors.toList());
//                    user.setDataScopeType(DataScopeType.CUSTOM.getValue());
//                    user.setDataScopeValues(unitIds);
//                }
//            } else if (DataScopeType.UNIT.getValue().equals(roleDataScope)) {
//                if (unitId == null) {
//                    //单位为空，只能查询自己
//                    user.setDataScopeType(DataScopeType.SELF.getValue());
//                } else {
//                    user.setDataScopeType(DataScopeType.UNIT.getValue());
//                    user.setDataScopeValues(Lists.newArrayList(unitId));
//                }
//            } else if (DataScopeType.UNIT_AND_CHILD.getValue().equals(roleDataScope)) {
//                if (unitId == null) {
//                    user.setDataScopeType(DataScopeType.SELF.getValue());
//                } else {
//                    List<UnitAncestor> unitAncestors = unitAncestorService.selectUnitAncestorsByAncestorId(unitId);
//                    if (CollectionUtils.isEmpty(unitAncestors)) {
//                        //单位为空，只能查询自己
//                        user.setDataScopeType(DataScopeType.SELF.getValue());
//                    } else {
//                        List<Long> unitIds = unitAncestors
//                                .stream()
//                                .map(UnitAncestor::getDescendantId)
//                                .collect(Collectors.toList());
//                        unitIds.add(0, unitId);
//                        user.setDataScopeType(DataScopeType.UNIT_AND_CHILD.getValue());
//                        user.setDataScopeValues(unitIds);
//                    }
//                }
//            } else if (DataScopeType.SELF.getValue().equals(roleDataScope)) {
//                user.setDataScopeType(DataScopeType.SELF.getValue());
//                user.setDataScopeValues(Lists.newArrayList(user.getUserId()));
//            } else {
//                //权限范围为空，只能查询自己
//                user.setDataScopeType(DataScopeType.SELF.getValue());
//                user.setDataScopeValues(Lists.newArrayList(user.getUserId()));
//            }
//        }
//        return user;
//    }

}
