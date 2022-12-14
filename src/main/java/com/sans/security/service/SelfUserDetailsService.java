package com.sans.security.service;

import com.sans.core.entity.SysUserEntity;
import com.sans.core.service.SysUserService;
import com.sans.security.entity.SelfUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * Spring Security 사용자 비지니스로직 구현
 */
@Component
@RequiredArgsConstructor
public class SelfUserDetailsService implements UserDetailsService {


    private final SysUserService sysUserService;

    /**
     * 사용자 정보 조회
     * @param username 사용자명
     * @return UserDetails SpringSecurity 사용자정보
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자정보 조회
        SysUserEntity sysUserEntity =sysUserService.selectUserByName(username);
        if (sysUserEntity!=null){
            // 파라미터 셋팅
            SelfUserEntity selfUserEntity = new SelfUserEntity();
            BeanUtils.copyProperties(sysUserEntity,selfUserEntity);
            return selfUserEntity;
        }
        return null;
    }
}
