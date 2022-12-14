package com.sans.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sans.core.entity.SysMenuEntity;
import com.sans.core.entity.SysRoleEntity;
import com.sans.core.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {


    List<SysRoleEntity> selectSysRoleByUserId(Long userId);

    List<SysMenuEntity> selectSysMenuByUserId(Long userId);
	
}
