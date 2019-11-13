package com.jk.service;

import com.jk.model.HousBean;

import java.util.Map;

/**
 * author:yangdd
 * create time:2019/11/12
 * email:2048114149@qq.com
 * tel:
 */
public interface YddHousService {
    /**
     * 房源审核
     * @param page
     * @param rows
     * @param hous
     * @return
     */
    Map<String, Object> yddHousList(Integer page, Integer rows, HousBean hous);
}
