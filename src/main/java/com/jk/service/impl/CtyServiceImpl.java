package com.jk.service.impl;

import com.alibaba.fastjson.JSON;
import com.jk.model.HousBean;
import com.jk.service.CtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Service
public class CtyServiceImpl implements CtyService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void jpfy(String[] ids) {
        String  jpfy = "jpfy";
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        List<HousBean> housBeans = mongoTemplate.find(query, HousBean.class);
        if(jedisCluster.exists(jpfy)){
            String s = jedisCluster.get(jpfy);
            List<HousBean> housBeans1 = JSON.parseArray(s, HousBean.class);
            Integer aa = housBeans.size()+housBeans1.size();
            if(housBeans.size()+housBeans1.size()>6){
                for (int i = 0;i <aa-6 ; i++) {
                    housBeans1.remove(0);
                }
                for (int i = 0; i <aa-6 ; i++) {
                    housBeans1.add(housBeans.get(i));
                }
                jedisCluster.set(jpfy, JSON.toJSONString(housBeans1));
            }else{
                for (int i = 0; i <housBeans.size() ; i++) {
                    housBeans1.add(housBeans.get(i));
                }
                jedisCluster.set(jpfy,JSON.toJSONString(housBeans1));
            }

        }else {

            jedisCluster.set(jpfy,JSON.toJSONString(housBeans));
        }
    }
}
