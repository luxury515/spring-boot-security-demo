package com.sans.security;

import com.sans.core.entity.SysMenuEntity;
import com.sans.core.service.SysUserService;
import com.sans.security.entity.SelfUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 어노테이션 권한인증
 */
@Component
@RequiredArgsConstructor
public class UserPermissionEvaluator implements PermissionEvaluator {

    private final SysUserService sysUserService;


    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        SelfUserEntity selfUserEntity =(SelfUserEntity) authentication.getPrincipal();
        Set<String> permissions = new HashSet<>();
        List<SysMenuEntity> sysMenuEntityList = sysUserService.selectSysMenuByUserId(selfUserEntity.getUserId());
        for (SysMenuEntity sysMenuEntity:sysMenuEntityList) {
            permissions.add(sysMenuEntity.getPermission());
        }
        if (permissions.contains(permission.toString())){
            return true;
        }
        return false;
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
