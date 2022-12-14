package com.sans.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sans.core.dao.SysMenuDao;
import com.sans.core.entity.SysMenuEntity;
import com.sans.core.service.SysMenuService;
import org.springframework.stereotype.Service;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

}
