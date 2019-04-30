package com.tomcatservlet.dao;

import com.tomcatservlet.javabean.Studen;
import com.tomcatservlet.jdbcutil.JdbcUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserJdbc {
    private JdbcTemplate jt;

    public Studen getStuden(Integer id, String username){
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "select * from studen where id=? and name=?";
        Studen studen = null;
        try{
            studen = jt.queryForObject(sql, new BeanPropertyRowMapper<>(Studen.class), id, username);
        }catch (Exception e){
        }
        return studen;
    }

    public boolean saveStuden(Integer id, String name, Integer age, Boolean gender) {
        jt = new JdbcTemplate(JdbcUtil.getDataSource());
        String sql = "insert into studen value(?,?,?,?)";
        int update = jt.update(sql, id, name, age, gender);
        boolean flag = false;
        if(1 == update){
            flag=true;
        }
        return flag;
    }



}
