package com.sans.security;

import com.sans.core.entity.SysRoleEntity;
import com.sans.core.service.SysUserService;
import com.sans.security.entity.SelfUserEntity;
import com.sans.security.service.SelfUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 커스텀 로그인 인증
 */
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final SelfUserDetailsService selfUserDetailsService;

    private final SysUserService sysUserService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        SelfUserEntity userInfo = selfUserDetailsService.loadUserByUsername(userName);
        if (userInfo == null) {
            throw new UsernameNotFoundException("사용자존재하지않음");
        }
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("패스워드틀림");
        }
        if (userInfo.getStatus().equals("PROHIBIT")){
            throw new LockedException("사용자가동결됨 ");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        List<SysRoleEntity> sysRoleEntityList = sysUserService.selectSysRoleByUserId(userInfo.getUserId());
        for (SysRoleEntity sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        userInfo.setAuthorities(authorities);
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
