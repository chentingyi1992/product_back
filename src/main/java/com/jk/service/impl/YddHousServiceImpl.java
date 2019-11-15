package com.jk.service.impl;

import com.jk.dao.YddHousDao;
import com.jk.model.AreaBean;
import com.jk.model.HeTongBean;
import com.jk.model.HousBean;
import com.jk.service.YddHousService;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 房源审核
     * @param page
     * @param rows
     * @param hous
     * @return
     */
    @Override
    public Map<String, Object> yddHousList(Integer page, Integer rows, HousBean hous) {

        HashMap<String, Object> map = new  HashMap<String, Object>();
        Query query=new  Query();
        long total=mongoTemplate.count(query, HousBean.class);
        int start=(page-1)*rows;
        query.skip(start);
        query.limit(rows);
        query.addCriteria(Criteria.where("status").in(1,2));
        List<HousBean> list=mongoTemplate.find(query, HousBean.class);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    /**
     * 修改审核状态
     * @param id
     */
    @Override
    public void shenhe(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("status",0);
        mongoTemplate.updateFirst(query,update,"t_housing");
    }

    /**
     * 查询合同
     * @param page
     * @param rows
     * @param hetong
     * @return
     */
    @Override
    public Map<String, Object> yddHetongList(Integer page, Integer rows, HeTongBean hetong) {
        HashMap<String, Object> map = new  HashMap<String, Object>();
        int total = yddHousDao.queryCount(hetong);
        int start = (page-1)*rows;
        List<HeTongBean> list = yddHousDao.yddHetongList(start,rows,hetong);
        map.put("total",total);
        map.put("rows",list);
        return map;
    }

    @Override
    public void addhetong(HeTongBean hetong) {
        yddHousDao.addhetong(hetong);
    }

    @Override
    public List<AreaBean> findDeptList() {
        return yddHousDao.findDeptList();
    }

    @Override
    public void del(Integer id) {
        yddHousDao.del(id);
    }


}
