package com.demo.mms.service;

import com.demo.mms.common.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> findAllStu();

    void deleteStuByID(String id);

    Student findStuByID(String id);
}
