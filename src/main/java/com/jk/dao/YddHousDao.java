package com.jk.dao;

import com.jk.model.HousBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 查询总条数
     * @param hous
     * @return
     */
    int queryCount(@Param("hous") HousBean hous);

    /**
     * 查询房源
     * @param start
     * @param rows
     * @param hous
     * @return
     */
    List<HousBean> yddHousList(@Param("start") int start,@Param("rows") Integer rows,@Param("hous") HousBean hous);
}
