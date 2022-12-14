package com.sans.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.core.dao.SysUserRoleDao;
import com.sans.core.entity.SysUserRoleEntity;
import com.sans.core.service.SysUserRoleService;
import org.springframework.stereotype.Service;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

}
