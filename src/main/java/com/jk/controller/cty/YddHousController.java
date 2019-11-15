package com.jk.controller.cty;

import com.jk.model.AreaBean;
import com.jk.model.HeTongBean;
import com.jk.model.HousBean;
import com.jk.service.YddHousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:yangdd
 * create time:2019/11/12
 * email:2048114149@qq.com
 * tel:
 */
@Controller
@RequestMapping("ydd")
public class YddHousController {

    @Autowired
    private YddHousService yddHousService;

    /**
     * 房源审核 yddHousList
     * @param page
     * @param rows
     * @param hous
     * @return
     */
    @RequestMapping("yddHousList")
    @ResponseBody
    public Map<String , Object> yddHousList(Integer page, Integer rows, HousBean hous){

        return yddHousService.yddHousList(page,rows,hous);
    }

    /**
     * 房源审核
     * @param id
     */
    @RequestMapping("shenhe")
    @ResponseBody
    public void shenhe(String id){
        yddHousService.shenhe(id);
    }

    /**
     * 查询合同
     * @param page
     * @param rows
     * @param hetong
     * @return
     */
    @RequestMapping("yddHetongList")
    @ResponseBody
    public Map<String , Object> yddHetongList(Integer page , Integer rows , HeTongBean hetong){
        return yddHousService.yddHetongList(page,rows,hetong);
    }

    /**
     * 新增
     * @param hetong
     */
    @RequestMapping("addhetong")
    @ResponseBody
    public void addhetong(HeTongBean hetong){
        yddHousService.addhetong(hetong);
    }

    /**
     * 查询小区
     * @return
     */
    @RequestMapping("findDeptList")
    @ResponseBody
    public List<AreaBean> findDeptList(){
        return yddHousService.findDeptList();
    }

    /**
     * 删除合同
     * @param id
     */
    @RequestMapping("del")
    @ResponseBody
    public void del(Integer id){
        yddHousService.del(id);
    }

}
