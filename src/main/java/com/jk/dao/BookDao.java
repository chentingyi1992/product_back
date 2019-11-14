package com.jk.dao;


import com.jk.model.*;
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



    @Select("select * from tt_user where userName=#{value}")
    UserBean findUserByName(String username);

    @Select("select * from t_area")
    List<AreaBean> findDeptList();

    @Select("select * from t_town t where t.areaId = #{id}")
    List<TownBean> queryTown(Integer id);

    @Select("select * from t_community t where t.townId = #{id}")
    List<CommBean> queryComm(Integer id);


}
