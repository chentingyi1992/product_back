package com.jk.controller.cty;


import com.jk.model.UserBean;
import com.jk.service.BookService;
import com.jk.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author：wdd
 * create time:2019/10/10
 * email：
 * tel：
 */
@Controller
@RequestMapping("book")
public class BookController {






    @Autowired
    private BookService bookService;
    @Bean(value = "bookService")
    public BookService getBookService() {
        return bookService;
    }

    //登录
    @RequestMapping("login")
    @ResponseBody
    public String login(UserBean user ){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken upt = new UsernamePasswordToken(user.getUserName(), user.getUserPwd());
        try{
            subject.login(upt);
            return "登录成功";
        }catch (UnknownAccountException e){
            return "账号不存在";
        }catch (IncorrectCredentialsException e){
            return "密码错误";
        }catch (AuthenticationException e){
            return "用户认证失败";
        }
    }
}
