package com.sans.security.handler;

import com.sans.common.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 로그인 하지는않은 사용에 대한 처리
 */
@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    /**
     * 로그인하지 않는 사용자일때 반환하는 부분
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(401,"未登录"));
    }
}
