package com.mmall.concurrency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试类
 * @author ligy
 * @date 2018/4/6 0006 22:15
 */
@Controller
public class Test {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "teset";
    }
}
