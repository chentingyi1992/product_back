package com.jk.dao;

import com.jk.model.AreaBean;
import com.jk.model.HeTongBean;
import com.jk.model.HousBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:yangdd
 * create time:2019/11/12
 * email:2048114149@qq.com
 * tel:
 */
@Repository
@Mapper
public interface YddHousDao {

    int queryCount(@Param("hetong") HeTongBean hetong);

    List<HeTongBean> yddHetongList(@Param("start") int start,@Param("rows") Integer rows,@Param("hetong") HeTongBean hetong);

    void addhetong(HeTongBean hetong);

    @Select("select * from t_area")
    List<AreaBean> findDeptList();

    @Delete("delete from t_hetong where id=#{value}")
    void del(Integer id);
}
