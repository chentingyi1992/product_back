package com.jk.service.impl;


import com.alibaba.fastjson.JSON;
import com.jk.dao.BookDao;
import com.jk.model.*;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
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
    @Autowired
    private JedisCluster jedisCluster;

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
        List<AreaBean> list= new ArrayList<>();
        String key="k2";
        if (jedisCluster.exists(key)){
            String s = jedisCluster.get(key);

            list= JSON.parseArray(s,AreaBean.class);
            System.out.println(list);

        }else {
            list=bookDao.findDeptList();
            String s = JSON.toJSONString(list);
            jedisCluster.set(key,s);
        }
        return list;
    }

    @Override
    public List<TownBean> queryTown(String name) {
        String key="k2";
        String s = jedisCluster.get(key);


        List<AreaBean> list = JSON.parseArray(s, AreaBean.class);
        System.out.println(list);
        AreaBean areaBean= new AreaBean();
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getName().equals(name)){
                areaBean.setId(list.get(i).getId());
                System.out.println( list.get(i));
            }
        }

        List<TownBean> listTown = new ArrayList<>();
        String keyTow=name+"k3";
        if (jedisCluster.exists(keyTow)){
            String Town = jedisCluster.get(keyTow);

            listTown= JSON.parseArray(Town,TownBean.class)
            ;
        }else {
            listTown= bookDao.queryTown(areaBean.getId());
            String s1 = JSON.toJSONString(listTown);
            jedisCluster.set(keyTow,s1);
        }



        return listTown;
    }


    @Override
    public List<CommBean> queryComm(String id,String name) {
        String key=name+"k3";
        String s = jedisCluster.get(key);

        List<TownBean> list= JSON.parseArray(s,TownBean.class);
        System.out.println(list);
        TownBean areaBean= new TownBean();

        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).getTownName().equals(id)){
                areaBean.setTownId(list.get(i).getTownId());
            }
        }
        List<CommBean> listTown = new ArrayList<>();
        String keyTow=id+"k4";
        if (jedisCluster.exists(keyTow)){
            String Town = jedisCluster.get(keyTow);

            listTown= JSON.parseArray(Town,CommBean.class);
        }else {
            listTown= bookDao.queryComm(areaBean.getTownId());
            String s1 = JSON.toJSONString(listTown);
            jedisCluster.set(keyTow,s1);
        }



        return listTown;
    }

    @Override
    public HousBean queryImgById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mogoTemplate.findOne(query,HousBean.class);
    }

}
