package com.demo.mms.dao;

import com.demo.mms.common.domain.Student;
import com.demo.mms.common.utils.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:cfg/spring-mybatis.xml", "classpath:cfg/spring-beans.xml"})
public class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;
    @Test
    public void addSomeStu(){
        for(int i = 1; i <= 88; i++){
            Student student = new Student();
            student.setId(IDGenerator.getId());
            student.setName("学员"+i);
            student.setSex(Math.random()>=0.5?1:0);
            student.setStunumber("stu_"+i);
            student.setBirthday(new Date());
            studentMapper.insert(student);
        }
        System.out.println("insertSucceed!");
    }
}