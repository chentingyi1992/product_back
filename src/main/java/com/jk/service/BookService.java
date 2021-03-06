package com.jk.service;


import com.jk.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * author：wdd
 * create time:2019/10/10
 * email：
 * tel：
 */
public interface BookService {


    UserBean findUserByName(String username);

    HashMap<String, Object> findBookList(Integer page, Integer rows, HousBean housBean);

    void addUser(HousBean housBean);

    List<AreaBean> findDeptList();

    List<TownBean> queryTown(String name);

    List<CommBean> queryComm(String id,String name);

    HousBean queryImgById(String id);
}
