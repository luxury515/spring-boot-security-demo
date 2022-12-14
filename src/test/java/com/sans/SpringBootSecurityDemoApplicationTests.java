package com.sans;

import com.sans.core.entity.SysUserEntity;
import com.sans.core.entity.SysUserRoleEntity;
import com.sans.core.service.SysUserRoleService;
import com.sans.core.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class SpringBootSecurityDemoApplicationTests {



    final private SysUserService sysUserService;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    final private SysUserRoleService sysUserRoleService;


    /**
     * 회원가입 테스트 코드
     */
    @Test
    public void contextLoads() {
        // admin 가입  -> role setting -> 1:ADMIN 2:USER
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("admin");
        sysUserEntity.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUserEntity.setStatus("ADMIN");
        sysUserService.save(sysUserEntity);

        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setRoleId(1L);
        sysUserRoleEntity.setUserId(sysUserEntity.getUserId());
        sysUserRoleService.save(sysUserRoleEntity);

        // user 가입
        SysUserEntity sysUserEntity2 = new SysUserEntity();
        sysUserEntity2.setUsername("user");
        sysUserEntity2.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUserEntity2.setStatus("NORMAL");
        sysUserService.save(sysUserEntity2);
        SysUserRoleEntity sysUserRoleEntity2 = new SysUserRoleEntity();
        sysUserRoleEntity2.setRoleId(2L);
        sysUserRoleEntity2.setUserId(sysUserEntity.getUserId());
        sysUserRoleService.save(sysUserRoleEntity2);
    }

}
