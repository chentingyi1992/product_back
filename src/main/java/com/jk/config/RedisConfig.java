package com.jk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Bean
    public JedisCluster getJedisCluster(){
        //分割集群节点
        String[] cNodes = clusterNodes.split(",");
        //创建set集合对象
        Set<HostAndPort> nodes =new HashSet<>();
        for (String node:cNodes) {

            //127.0.0.1:7001
            String[] hp = node.split(":");
            System.out.println(hp[0]+""+hp[1]);
            nodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
        }
        //创建Redis集群对象
        JedisCluster jedisCluster=new JedisCluster(nodes);
        return jedisCluster;
    }
}
