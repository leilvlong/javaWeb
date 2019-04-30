package com.tomcatservlet.javabean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class DataBean {

    public static <T> T dataBean(Class<T> obj , Map<String, String[]> studenMsg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        T stu =  obj.getDeclaredConstructor().newInstance();

        Set<String> studenPropertys = studenMsg.keySet();
        Field studenField = null;
        Class<?> type;
        for (String studenProperty : studenPropertys) {
            try{
                studenField = obj.getDeclaredField(studenProperty);
            }catch (Exception e){
                throw new RuntimeException("JavaBean is wrong or HttpRequest parameter error");
            }
            studenField.setAccessible(true);
            String value = studenMsg.get(studenProperty)[0];
            type = studenField.getType();
            try {
                studenField.set(stu, type.getDeclaredConstructor(String.class).newInstance(value));
            }catch (Exception e){
                throw new RuntimeException("JavaBean is wrong");
            }
        }
        return stu;
    }


}
