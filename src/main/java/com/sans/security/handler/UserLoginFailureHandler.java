package com.sans.security.handler;

import com.sans.common.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 로그인 실패시 처리
 */
@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 로그인 실패시 리턴
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){

        if (exception instanceof UsernameNotFoundException){
            log.info("[로그인실패!]"+exception.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"존재하지 않는 사용자!"));
        }
        if (exception instanceof LockedException){
            log.info("[로그인실패!]"+exception.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"동결된 사용자!"));
        }
        if (exception instanceof BadCredentialsException){
            log.info("[로그인실패!]"+exception.getMessage());
            ResultUtil.responseJson(response,ResultUtil.resultCode(500,"패스워드가 틀림."));
        }
        ResultUtil.responseJson(response,ResultUtil.resultCode(500,"로그인 실패!"));
    }
}
