package com.sans.common.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class ResultUtil {


    private ResultUtil(){}


    public static void responseJson(ServletResponse response, Map<String, Object> resultMap){
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception e) {
            log.error("[JSON output exception 발생:]"+e);
        }finally{
            if(out!=null){
                out.flush();
                out.close();
            }
        }
    }

    public static Map<String, Object> resultSuccess(Map<String, Object> resultMap){
        resultMap.put("message","성공!");
        resultMap.put("code", 200);
        return resultMap;
    }

    public static Map<String, Object> resultError(Map<String, Object> resultMap){
        resultMap.put("message","실패!");
        resultMap.put("code",500);
        return resultMap;
    }

    public static Map<String, Object> resultCode(Integer code,String msg){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message",msg);
        resultMap.put("code",code);
        return resultMap;
    }

}
