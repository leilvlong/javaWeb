package com.tomcat.service;

import com.tomcat.doman.Contact;
import com.tomcat.doman.PageBean;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IServiceContact {
    // 查询所有联系人
    List<Contact> getAllContact();

    // 分页查询联系人
    PageBean getPageContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException;

    // 添加联系人
    boolean addContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException;

    //删除联系人
    boolean delContace(HttpServletRequest request);

    // 修改联系人
    boolean updateContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException;
}
