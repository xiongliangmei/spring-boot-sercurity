package com.xl.service;

import com.xl.model.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private  SysRoleService sysRoleService;
    //判断权限
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        User user = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities =  user.getAuthorities();
        //遍历所有角色
        for (GrantedAuthority authority:authorities) {
            String roleName = authority.getAuthority(); //角色名称
            Integer roleId = sysRoleService.selectByName(roleName);
            List<SysPermission> list = sysPermissionService.listByRoleId(roleId);
            for (SysPermission permission:list) {
                //获取权限集合
                List permissions = permission.getPermissions();
                if (targetUrl.equals(permission.getUrl())&&permissions.contains(targetPermission)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
