package com.tomcatservlet.service;

import com.tomcatservlet.dao.UserJdbc;
import com.tomcatservlet.javabean.Studen;

import java.util.Map;

public class UserAction {
    private UserJdbc uj = new UserJdbc();

    public Studen getStuden(String id, String username){
        Studen studen = null;
        try{
            Integer newID = Integer.parseInt(id);
            if(username.length()>4){
                return studen;
            }
            studen = uj.getStuden(newID, username);
        }catch (Exception e){

        }
        return studen;
    }

    public boolean registerStuden(Studen studen){
        Integer id = studen.getId();
        String name = studen.getName();
        Integer age = studen.getAge();
        Boolean gender = studen.getGender();
        boolean savaStatus = uj.saveStuden(id, name, age, gender);
        return savaStatus;
    }

}
