package com.ilee.elecshop.interceptor;

import com.ilee.elecshop.util.JwtUtils;
import com.ilee.elecshop.util.StringUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        System.out.println(path);
        if(handler instanceof HandlerMethod) {
            String token = request.getHeader("token");
            if(StringUtil.isEmpty(token)) {
                System.out.println("token为空");
                throw new RuntimeException("q签名验证不存在");
            }else {
                Claims claims = JwtUtils.validateJWT(token).getClaims();
                if(path.startsWith("/admin")) {
                    if (claims == null || !claims.getSubject().equals("admin") || !claims.getId().equals("-1")){
                        System.out.println("管理员鉴权失败");
                        throw new RuntimeException("管理员鉴权失败");
                    }else {
                        System.out.println("管理员鉴权成功");
                        return true;
                    }
                }else {
                    if(claims == null) {
                        System.out.println("用户鉴权失败");
                        throw new RuntimeException("用户鉴权失败");
                    }else {
                        System.out.println("用户鉴权成功");
                        return true;
                    }
                }
            }
        }else {
            return true;
        }
    }
}
