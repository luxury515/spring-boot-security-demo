package com.sans.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.core.dao.SysRoleDao;
import com.sans.core.entity.SysRoleEntity;
import com.sans.core.service.SysRoleService;
import org.springframework.stereotype.Service;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

}
