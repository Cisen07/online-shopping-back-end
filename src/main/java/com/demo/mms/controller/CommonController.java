package com.demo.mms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
    @RequestMapping("/page/{path}")
    public String toLoginPage(@PathVariable("path")String path) {
        return path;
    }
}
