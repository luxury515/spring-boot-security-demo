package com.sans.common.util;

import com.sans.security.entity.SelfUserEntity;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {


    private SecurityUtil(){}


    public static SelfUserEntity getUserInfo(){
        SelfUserEntity userDetails = (SelfUserEntity) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userDetails;
    }

    public static Long getUserId(){
        return getUserInfo().getUserId();
    }

    public static String getUserName(){
        return getUserInfo().getUsername();
    }
}
