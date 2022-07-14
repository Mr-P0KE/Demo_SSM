package com.rsk.controller;

import com.rsk.bean.Department;
import com.rsk.bean.Msg;
import com.rsk.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理与部门相关的请求
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/depts")
    public Msg ss(){
        List<Department> list = departmentService.getAll();
        return Msg.success().add("depts",list);
    }
}
