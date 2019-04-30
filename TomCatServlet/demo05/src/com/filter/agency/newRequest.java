package com.filter.agency;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/*
动态代理request请求
 */
public class newRequest implements InvocationHandler {
    private HttpServletRequest request;
    private List<String> strs = new ArrayList<>();
    public newRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        try{
            Method requestMethod = this.getClass().getDeclaredMethod(methodName, parameterTypes);
            return requestMethod.invoke(this, args);
        }catch (NoSuchMethodException e){
            return method.invoke(request,args);
        }

    }

    public String getParameter(String name) throws UnsupportedEncodingException {
        if(strs.isEmpty()){
            addStr();
        }

        String parameter = request.getParameter(name);
        for (String str : strs) {
            if(parameter.contains(str)){
                parameter = parameter.replace(str,"***");
            }
        }

        return parameter;
    }

    private void addStr() throws UnsupportedEncodingException {
        InputStream rs = this.getClass().getClassLoader().getResourceAsStream("comment.txt");
        InputStreamReader sr = new InputStreamReader(rs,"utf-8");
        BufferedReader br = new BufferedReader(sr);
        String str;
        try{

            while ((str = br.readLine()) != null){
                strs.add(str);
            }
        }catch (Exception e){

        }finally {
            try {
                br.close();
                sr.close();
                rs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
