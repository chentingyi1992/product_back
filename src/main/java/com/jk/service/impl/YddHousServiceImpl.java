package com.jk.service.impl;

import com.jk.dao.YddHousDao;
import com.jk.model.HousBean;
import com.jk.service.YddHousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:yangdd
 * create time:2019/11/12
 * email:2048114149@qq.com
 * tel:
 */
@Service
public class YddHousServiceImpl implements YddHousService {

    @Autowired
    private YddHousDao yddHousDao;

    /**
     * 房源审核
     * @param page
     * @param rows
     * @param hous
     * @return
     */
    @Override
    public Map<String, Object> yddHousList(Integer page, Integer rows, HousBean hous) {

        HashMap<String, Object> map = new HashMap<>();

        int total = yddHousDao.queryCount(hous);

        int start = (page-1)*rows;

        List<HousBean> list = yddHousDao.yddHousList(start,rows,hous);

        map.put("total",total);

        map.put("rows",list);

        return map;
    }
}
