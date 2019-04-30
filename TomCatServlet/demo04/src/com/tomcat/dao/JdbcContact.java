package com.tomcat.dao;

import com.tomcat.doman.Contact;
import com.tomcat.util.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcContact implements IJdbcContact{
    private JdbcTemplate jt = new JdbcTemplate(JdbcUtil.getDataSource());

    // 获取所有联系人
    @Override
    public List<Contact> getAllContact(){
        String sql = "SELECT * FROM contact";
        List<Contact> query = null;
        try {
            query = jt.query(sql, new BeanPropertyRowMapper<>(Contact.class));
        }catch (Exception ignored){
        }
        return query;
    }

    // 获取数据分页查询sql
    @Override
    public List<Contact> getPageContact(Object[] limit){
        String sql = "select * from contact limit ?,?";
        List<Contact> query = null;
        try {
            query = jt.query(sql, new BeanPropertyRowMapper<>(Contact.class),limit);
        }catch (Exception ignored){
        }
        return query;
    }

    @Override
    public Integer getCountContact(){
        String sql = "select count(id) from contact";
        Integer count=0;
        try{
             count = jt.queryForObject(sql, Integer.class);
        }catch (Exception e){
        }
        return count;
    }


    // 添加联系人
    @Override
    public boolean addContact(Object[] contactProperty) {
        String sql = "insert into contact value(?,?,?,?,?,?,?)";
        return getSqlResult(jt,sql,contactProperty);
    }

    //删除联系人
    @Override
    public boolean delContace(Object[] contactProperty) {
        String sql = "delete from contact where id=? and name=?";
        return getSqlResult(jt,sql,contactProperty);
    }

    // 修改联系人
    @Override
    public boolean updateConatct(Object[] contactProperty){
        String sql = "update contact set " +
                "sex=?, age=?, address=?, qq=?,email=? where id=? and name=?";
        return getSqlResult(jt,sql,contactProperty);
    }

    // 获取增删改的结果
    @Override
    public boolean getSqlResult(JdbcTemplate jt, String sql, Object[] contactProperty){
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
