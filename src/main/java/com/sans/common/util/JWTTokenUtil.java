package com.sans.common.util;

import com.alibaba.fastjson.JSON;
import com.sans.common.config.JWTConfig;
import com.sans.security.entity.SelfUserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import java.util.*;


@Slf4j
public class JWTTokenUtil {

    private JWTTokenUtil(){}


    public static String createAccessToken(SelfUserEntity selfUserEntity){
        // jwt 생성 부분
        String token = Jwts.builder()
                .setId(selfUserEntity.getUserId()+"")
                .setSubject(selfUserEntity.getUsername())
                // 발급시간
                .setIssuedAt(new Date())
                // 발급자 임의로 수정가능
                .setIssuer("demo-user")
                // 권한설정
                .claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
                // 만료시간
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                // 싸인알고리즘, yml에 기재된 시큐릿 key값
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
        return token;
    }
}
