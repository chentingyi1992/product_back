package com.jk.controller.cty;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cty")
public class Controller_Cty {

    @RequestMapping("toMain")
    public String toMain(){
        return "cty/main";
    }
}
