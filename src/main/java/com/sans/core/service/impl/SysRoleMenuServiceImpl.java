package com.sans.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.core.dao.SysRoleMenuDao;
import com.sans.core.entity.SysRoleMenuEntity;
import com.sans.core.service.SysRoleMenuService;
import org.springframework.stereotype.Service;


@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

}
