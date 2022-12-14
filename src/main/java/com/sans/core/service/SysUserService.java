package com.sans.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sans.core.entity.SysMenuEntity;
import com.sans.core.entity.SysRoleEntity;
import com.sans.core.entity.SysUserEntity;
import java.util.List;


public interface SysUserService extends IService<SysUserEntity>  {


    SysUserEntity selectUserByName(String username);

    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysMenuEntity> selectSysMenuByUserId(Long userId);

}
