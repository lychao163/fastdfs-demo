package com.lychao163.fastdfsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description TODO
 * @Date 2019/4/18 18:37
 * @Author lychao
 */
@Controller
public class IndexController {

    @RequestMapping("helloDfs")
    @ResponseBody
    public ModelAndView helleIndex(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
