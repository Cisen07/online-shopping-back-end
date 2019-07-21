package com.demo.mms.controller;

import com.demo.mms.common.domain.Student;
import com.demo.mms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/list")
    public String listStu(ModelMap modelMap) {
        System.out.println("查询学生数据");
        List<Student> allStudent = studentService.findAllStu();
        modelMap.put("allStu", allStudent);
        return "stu_list";
    }

    @RequestMapping("del")
    @ResponseBody   //如果返回值是json格式，而不是视图，就得加这个
    public Object delStu(String id){
        studentService.deleteStuByID(id);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @RequestMapping("/toupdate")
    public String toUpDate(ModelMap modelMap, String id){
        Student student = studentService.findStuByID(id);
        modelMap.put("stu", student);
        return "showStu4Update";
    }
}
