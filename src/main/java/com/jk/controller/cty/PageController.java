package com.jk.controller.cty;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author：wdd
 * create time:2019/10/12
 * email：
 * tel：
 */
@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("toLogin")
    public String toLogin(){
        return "cty/login";
    }

    @RequestMapping("main")
    public String main(){
        return "cty/main";
    }

    @RequestMapping("rolepower")
    public String rolepower(){
        return "cty/rolepower";
    }

    @RequestMapping("userlist")
    @RequiresPermissions("page:userlist")
    public String userlist(){
        return "cty/userlist";
    }
}
