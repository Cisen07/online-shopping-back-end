package com.demo.mms.service;

import com.demo.mms.common.domain.Student;
import com.demo.mms.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAllStu() {
        return studentMapper.selectAllStudent();
    }

    @Override
    @Transactional
    public void deleteStuByID(String id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Student findStuByID(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
