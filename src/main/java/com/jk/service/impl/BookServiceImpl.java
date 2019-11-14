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
    private JedisCluster jedisCluster;
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

        List<AreaBean> list= new ArrayList<>();
        String key="k2";
        //jedisCluster.set(key,"1123");
        if (jedisCluster.exists(key)){
            String s = jedisCluster.get(key);

            list= (List<AreaBean>) JSON.parse(s);
            System.out.println(list);

        }else {
            list=bookDao.findDeptList();
            jedisCluster.set(key,String.valueOf(list));
        }

        String keyTown="k3";
        List<TownBean> listTown= new ArrayList<>();
        if (jedisCluster.exists(keyTown)){
            String s = jedisCluster.get(keyTown);

            listTown= (List<TownBean>) JSON.parse(s);
            System.out.println(listTown);

        }else {
            listTown=bookDao.queryList();
            String s = JSON.toJSONString(listTown);
            jedisCluster.set(keyTown,s);
        }

        String keyCom="k4";
        List<CommBean> listCom= new ArrayList<>();
        if (jedisCluster.exists(keyCom)){
            String s = jedisCluster.get(keyCom);

            listCom= (List<CommBean>) JSON.parse(s);
            System.out.println(listCom);

        }else {
            listCom=bookDao.queryCom();
            String s = JSON.toJSONString(listCom);
            jedisCluster.set(keyCom,s);
        }




        return list;
    }

    @Override
    public List<TownBean> queryTown(String name) {

        List<AreaBean> listArea= new ArrayList<>();
        String keyArea="k2";
        //jedisCluster.set(key,"1123");
        if (jedisCluster.exists(keyArea)){
            String s = jedisCluster.get(keyArea);

            listArea= (List<AreaBean>) JSON.parse(s);
            System.out.println(listArea);

        }else {
            listArea=bookDao.findDeptList();
            jedisCluster.set(keyArea,String.valueOf(listArea));
        }


        AreaBean areaBean= new AreaBean();
        for (int i = 0; i <listArea.size() ; i++) {
            if (listArea.get(i).getName().equals(name)){
                areaBean=listArea.get(i);
            }
        }




        return bookDao.queryTown(areaBean.getId());
    }



    @Override
    public List<CommBean> queryComm(String townName) {
        String keyTown="k3";
        List<TownBean> listTown= new ArrayList<>();
        if (jedisCluster.exists(keyTown)){
            String s = jedisCluster.get(keyTown);

            listTown= (List<TownBean>) JSON.parse(s);
            System.out.println(listTown);

        }else {
            listTown=bookDao.queryList();
            String s = JSON.toJSONString(listTown);
            jedisCluster.set(keyTown,s);
        }

        TownBean townBean= new TownBean();
        for (int i = 0; i <listTown.size() ; i++) {
            if (listTown.get(i).getTownName().equals(townName)){
                townBean=listTown.get(i);
            }
        }
        return bookDao.queryComm(townBean.getTownId());
    }

    @Override
    public HousBean queryImgById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mogoTemplate.findOne(query,HousBean.class);
    }

}
