package com.sans.security.handler;

import com.sans.common.util.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 권한 없을시 처리하는 부분
 */
@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler{

    /**
     * 권한없을시 반환하는 부분
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(403,"권한없음!"));
    }
}
