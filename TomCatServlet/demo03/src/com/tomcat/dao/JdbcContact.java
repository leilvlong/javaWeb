package com.tomcat.dao;

import com.tomcat.doman.Contact;
import com.tomcat.util.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcContact {
    private JdbcTemplate jt;

    // 获取所有联系人
    public List<Contact> getAllContact(){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "SELECT * FROM contact";
        List<Contact> query = null;
        try {
            query = jt.query(sql, new BeanPropertyRowMapper<>(Contact.class));
        }catch (Exception ignored){
        }
        return query;
    }

    // 获取数据分页查询sql
    public List<Contact> getPageContact(Object[] limit){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "select * from contact limit ?,?";
        List<Contact> query = null;
        try {
            query = jt.query(sql, new BeanPropertyRowMapper<>(Contact.class),limit);
        }catch (Exception ignored){
        }
        return query;
    }

    public Integer getCountContact(){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "select count(id) from contact";
        return jt.queryForObject(sql, Integer.class);
    }


    // 添加联系人
    public boolean addContact(Object[] contactProperty) {
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "insert into contact value(?,?,?,?,?,?,?)";

        return getSqlResult(jt,sql,contactProperty);
    }

    //删除联系人
    public boolean delContace(Object[] contactProperty) {
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "delete from contact where id=? and name=?";

        return getSqlResult(jt,sql,contactProperty);
    }

    // 修改联系人
    public boolean updateConatct(Object[] contactProperty){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "update contact set " +
                "sex=?, age=?, address=?, qq=?,email=? where id=? and name=?";
        return getSqlResult(jt,sql,contactProperty);
    }

    // 获取增删改的结果
    private boolean getSqlResult(JdbcTemplate jt,String sql, Object[] contactProperty){
        boolean flag = false;
        try {
            int update = jt.update(sql, contactProperty);
            if (update == 1) {
                flag = true;
            }
        }catch (Exception e){
        }
        return flag;
    }
}
