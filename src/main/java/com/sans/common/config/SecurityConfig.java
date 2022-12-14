package com.sans.common.config;

import com.sans.security.UserPermissionEvaluator;
import com.sans.security.handler.*;
import com.sans.security.UserAuthenticationProvider;
import com.sans.security.jwt.JWTAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 요거 추가하면 controller 쪽에 어노테이션으로 권한 처리 할수 있음.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoginSuccessHandler userLoginSuccessHandler;

    private final UserLoginFailureHandler userLoginFailureHandler;

    private final UserLogoutSuccessHandler userLogoutSuccessHandler;

    private final UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;

    private final UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;

    private final UserAuthenticationProvider userAuthenticationProvider;

    private final UserPermissionEvaluator userPermissionEvaluator;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
               .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()
                .anyRequest().authenticated()
                .and()

                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .formLogin()
                .loginProcessingUrl("/login/userLogin")
                .successHandler(userLoginSuccessHandler)
                .failureHandler(userLoginFailureHandler)
                .and()

                .logout()
                .logoutUrl("/login/userLogout")
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                .cors()
                .and()
                .csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));
    }
}
