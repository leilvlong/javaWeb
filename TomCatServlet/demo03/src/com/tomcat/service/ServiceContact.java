package com.tomcat.service;

import com.tomcat.dao.JdbcContact;
import com.tomcat.doman.Contact;
import com.tomcat.doman.PageBean;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ServiceContact {
    private JdbcContact jt;

    // 查询所有联系人
    public List<Contact> getAllContact(){
        jt = new JdbcContact();
        return jt.getAllContact();
    }

    // 分页查询联系人
    public PageBean getPageContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {

        jt = new JdbcContact();
        // 设置pageBean
        PageBean pageBean = new PageBean();
        // 设置总数据条数
        Integer countContact = jt.getCountContact();
        pageBean.setTotalSize(countContact);
        //设置每页条数
        pageBean.setPageSize(8);
        // 设置总页数
        Integer pageCount = countContact % 8 == 0 ? countContact / 8 : countContact / 8 + 1;
        pageBean.setTotalPage(pageCount);
        // 设置当前页数
        String curPage = request.getParameter("curPage");
        if (curPage != null) {
            pageBean.setCurPage(Long.parseLong(curPage));
        } else {
            pageBean.setCurPage(1);
        }

        // 获取分页数据并将其装入pageBean容器
        Long curPage1 = pageBean.getCurPage();
        Integer pageSize = pageBean.getPageSize();
        curPage1 = (curPage1-1)*pageSize;
        Object[] limit = {curPage1,pageSize};
        List<Contact> pageContact = jt.getPageContact(limit);
        pageBean.setList(pageContact);

        return pageBean;
    }




    // 添加联系人
    public boolean addContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        // 获取提交表单信息
        Map<String, String[]> contactMap = request.getParameterMap();
        // 设置该对象为实体对象并获取属性
        Contact contact = new Contact();
        BeanUtils.populate(contact,contactMap);
        Object[] contactProperty = {
                contact.getId(),
                contact.getName(),
                contact.getSex(),
                contact.getAge(),
                contact.getAddress(),
                contact.getQq(),
                contact.getEmail()
        };
        //获取数据操作对象
        jt = new JdbcContact();
        return jt.addContact(contactProperty);

    }


    //删除联系人
    public boolean delContace(HttpServletRequest request){


        // 获取删除信息 因为id唯一并且根据id与用户名删除不会有问题
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Object[] contactProperty = {Integer.parseInt(id),name};
        jt = new JdbcContact();
        return jt.delContace(contactProperty);
    }


    // 修改联系人
    public boolean updateContact(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        // 获取提交表单信息
        Map<String, String[]> contactMap = request.getParameterMap();
        // 设置该对象为实体对象并获取属性
        Contact contact = new Contact();
        BeanUtils.populate(contact,contactMap);
        Object[] contactProperty = {
                contact.getSex(),
                contact.getAge(),
                contact.getAddress(),
                contact.getQq(),
                contact.getEmail(),
                contact.getId(),
                contact.getName()
        };

        jt = new JdbcContact();

        return jt.updateConatct(contactProperty);
    }
}
