package com.jk.controller.cty;

import com.jk.model.AreaBean;
import com.jk.model.CommBean;
import com.jk.model.HousBean;
import com.jk.model.TownBean;
import com.jk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("yzy")
public class YzyController {

    @Autowired
    private BookService bookService;

    @RequestMapping("yddHousList")
    @ResponseBody
    public HashMap<String, Object> findBookList(Integer page, Integer rows, HousBean housBean){
        return bookService.findBookList(page,rows,housBean);
    }

    //新增和修改
    @RequestMapping("addHous")
    @ResponseBody
    public void addUser(HousBean housBean){
        bookService.addUser(housBean);
    }

    @RequestMapping("findDeptList")
    @ResponseBody
    public List<AreaBean> findDeptList(){
        return bookService.findDeptList();
    }


    @RequestMapping("queryTown")
    @ResponseBody
    public List<TownBean> queryTown(Integer id){
        return bookService.queryTown(id);
    }

    @RequestMapping("queryComm")
    @ResponseBody
    public List<CommBean> queryComm(Integer id){
        return bookService.queryComm(id);
    }
}
