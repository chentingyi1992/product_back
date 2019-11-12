package com.jk.service.impl;


import com.jk.dao.BookDao;
import com.jk.model.UserBean;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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


    @Override
    public UserBean findUserByName(String username) {
        return bookDao.findUserByName(username);
    }
}
