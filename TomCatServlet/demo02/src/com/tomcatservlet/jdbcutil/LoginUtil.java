package com.tomcatservlet.jdbcutil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LoginUtil {
    public static Cookie getCookie(String key,String value, int setMaxAge, String path){
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath(path);
        return cookie;
    }


}
