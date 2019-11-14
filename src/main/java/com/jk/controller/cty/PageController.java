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


    @RequestMapping("fangyuan")
    public String fangyuan(){
        return "cty/fangyuan";

        @RequestMapping("toyonghu")
        public String toyonghu(){
            return "cty/toyonghu";

        }

        @RequestMapping("userlist")
        @RequiresPermissions("page:userlist")
        public String userlist(){
            return "cty/userlist";
        }

        /**
         * ydd房源审核   tohous
         * @return
         */
        @RequestMapping("tohous")
        public String tohous(){
            return "ydd/hous";
        }
        @RequestMapping("toXiaoQu")
        public String toXiaoQu(){
            return "cty/toXiaoQu";
        }
    }
    }
