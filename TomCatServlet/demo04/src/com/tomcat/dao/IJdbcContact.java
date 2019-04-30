package com.tomcat.dao;

import com.tomcat.doman.Contact;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface IJdbcContact {
    // 获取所有联系人
    List<Contact> getAllContact();

    // 获取数据分页查询sql
    List<Contact> getPageContact(Object[] limit);

    Integer getCountContact();

    // 添加联系人
    boolean addContact(Object[] contactProperty);

    //删除联系人
    boolean delContace(Object[] contactProperty);

    // 修改联系人
    boolean updateConatct(Object[] contactProperty);

    // 获取增删改的结果
    boolean getSqlResult(JdbcTemplate jt, String sql, Object[] contactProperty);
}
