package com.jk.dao;


import com.jk.model.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author：wdd
 * create time:2019/10/10
 * email：
 * tel：
 */
@Repository
@Mapper
public interface BookDao {



    @Select("select * from t_user where userName=#{value}")
    UserBean findUserByName(String username);
}
