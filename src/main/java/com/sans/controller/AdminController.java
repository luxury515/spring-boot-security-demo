package com.sans.controller;

import com.sans.common.util.ResultUtil;
import com.sans.common.util.SecurityUtil;
import com.sans.core.entity.SysMenuEntity;
import com.sans.core.entity.SysRoleEntity;
import com.sans.core.entity.SysUserEntity;
import com.sans.core.service.SysMenuService;
import com.sans.core.service.SysRoleService;
import com.sans.core.service.SysUserService;
import com.sans.security.entity.SelfUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    private final SysMenuService sysMenuService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public Map<String,Object> userLogin(){
        Map<String,Object> result = new HashMap<>();
        SelfUserEntity userDetails = SecurityUtil.getUserInfo();
        result.put("title","관리자정보");
        result.put("data",userDetails);
        return ResultUtil.resultSuccess(result);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String,Object> result = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        result.put("title","user 와 admin role 모두 조회 가능!");
        result.put("data",sysUserEntityList);
        return ResultUtil.resultSuccess(result);
    }


    @PreAuthorize("hasRole('ADMIN') and hasRole('USER')")
    @RequestMapping(value = "/menuList",method = RequestMethod.GET)
    public Map<String,Object> menuList(){
        Map<String,Object> result = new HashMap<>();
        List<SysMenuEntity> sysMenuEntityList = sysMenuService.list();
        result.put("title","user 와 admin role 모두 조회 가능!");
        result.put("data",sysMenuEntityList);
        return ResultUtil.resultSuccess(result);
    }



    @PreAuthorize("hasPermission('/admin/userList','sys:user:info')")
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public Map<String,Object> userList(){
        Map<String,Object> result = new HashMap<>();
        List<SysUserEntity> sysUserEntityList = sysUserService.list();
        result.put("title","sys:user:info 권한은 모두 조회가능!");
        result.put("data",sysUserEntityList);
        return ResultUtil.resultSuccess(result);
    }



    @PreAuthorize("hasRole('ADMIN') and hasPermission('/admin/adminRoleList','sys:role:info')")
    @RequestMapping(value = "/adminRoleList",method = RequestMethod.GET)
    public Map<String,Object> adminRoleList(){
        Map<String,Object> result = new HashMap<>();
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.list();
        result.put("title","ADMIN role 과 sys:role:info 권한만 방문가능!");
        result.put("data",sysRoleEntityList);
        return ResultUtil.resultSuccess(result);
    }
}
