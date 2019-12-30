package com.itheima.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {

    public static Cookie findCookie(Cookie[] cookies,String lastTime){
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(lastTime)){
                    return cookie;
                }
            }
        }
        return null;
    }
}

