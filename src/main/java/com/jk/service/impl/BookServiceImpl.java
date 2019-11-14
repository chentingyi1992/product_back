package com.jk.service.impl;


import com.jk.dao.BookDao;
import com.jk.model.*;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * author：wdd
 * create time:2019/10/10
 * email：
 * tel：
 */
@Service //选dubbo包下
@Component
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private MongoTemplate mogoTemplate;

    @Override
    public UserBean findUserByName(String username) {
        return bookDao.findUserByName(username);
    }

    @Override
    public HashMap<String, Object> findBookList(Integer page, Integer rows, HousBean housBean) {
        HashMap<String, Object> map = new  HashMap<String, Object>();
        Query query=new  Query();
        if (housBean.getTitle()!=""&&housBean.getTitle()!=null) {
            query.addCriteria(Criteria.where("title").regex(housBean.getTitle()));
        }
        long	total=mogoTemplate.count(query, HousBean.class);
        int  start=(page-1)*rows;
        query.skip(start);
        query.limit(rows);
        List<HousBean> list=mogoTemplate.find(query, HousBean.class);
        map.put("total", total);
        map.put("rows", list);
        return map;
    }

    @Override
    public void addUser(HousBean housBean) {
        /*if (housBean.getId().equals("")) {
            housBean.setId(null);
        }*/
        mogoTemplate.save(housBean);
    }

    @Override
    public List<AreaBean> findDeptList() {
        return bookDao.findDeptList();
    }

    @Override
    public List<TownBean> queryTown(Integer id) {
        return bookDao.queryTown(id);
    }

    @Override
    public List<CommBean> queryComm(Integer id) {
        return bookDao.queryComm(id);
    }

}
