package com.rsk.service;

import com.rsk.bean.Department;
import com.rsk.bean.Employee;
import com.rsk.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;


    public List<Department> getAll(){
        return departmentMapper.selectByExample(null);
    }
}
