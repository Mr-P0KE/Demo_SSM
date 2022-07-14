package com.rsk.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rsk.bean.Employee;
import com.rsk.bean.Msg;
import com.rsk.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//处理员工crud
@Controller
public class EmployeeController {
     @Autowired
     EmployeeService employeeService;

     @RequestMapping("/emps/{pn}")
     @ResponseBody
     public Msg  getEmpWithJSON(@PathVariable("pn")Integer pn){
         PageHelper.startPage(pn,10);
         List<Employee> list=employeeService.getAll();

         //包装查询结果，只需交给页面就行
         //里面封装了详细的分页信息，包括查询数据，连续显示的页数
         PageInfo pageInfo = new PageInfo(list,5);
         return Msg.success().add("pageInfo",pageInfo);

     }
    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate-Validator
     *
     *
     * @return
     */
     @RequestMapping(value = "/emp",method = RequestMethod.POST)
     @ResponseBody
     public Msg saveEmp(@Valid Employee employee, BindingResult result){
         if(result.hasErrors()){
             Map<String,Object> map = new HashMap<>();
             List<FieldError> fieldErrors = result.getFieldErrors();
             for(FieldError fieldError : fieldErrors){
                 System.out.println("错误字段名 " +fieldError.getField());
                 System.out.println("错误信息 "+fieldError.getDefaultMessage());
                 map.put(fieldError.getField(),fieldError.getDefaultMessage());
             }
             return Msg.fail().add("errorFiled",map);
         }else {
             employeeService.saveEmp(employee);
             return Msg.success();
         }
     }

     @RequestMapping(value = "/checkuser")
     @ResponseBody
    public Msg checkUser( String empName){
         boolean b = employeeService.checkUser(empName);
         //数据库用户名重复校验
         if(b)
             return Msg.success();

         return Msg.fail();
     }

    @ResponseBody
    @RequestMapping("/emp/{id}")
    public Msg getEmp(@PathVariable("id")Integer id){
         Employee emp = employeeService.getEmp(id);
         return Msg.success().add("emp",emp);
     }

    /**
     * 如果直接发送ajax=PUT形式的请求
     * 封装的数据
     * Employee
     * [empId=1014, empName=null, gender=null, email=null, dId=null]
     *
     * 问题：
     * 请求体中有数据；
     * 但是Employee对象封装不上；
     * update tbl_emp  where emp_id = 1014;
     *
     * 原因：
     * Tomcat：
     * 		1、将请求体中的数据，封装一个map。
     * 		2、request.getParameter("empName")就会从这个map中取值。
     * 		3、SpringMVC封装POJO对象的时候。
     * 				会把POJO中每个属性的值，request.getParamter("email");
     * AJAX发送PUT请求引发的血案：
     * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
     * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
     * org.apache.catalina.connector.Request--parseParameters() (3111);
     *
     * protected String parseBodyMethods = "POST";
     * if( !getConnector().isParseBodyMethod(getMethod()) ) {
     success = true;
     return;
     }
     *
     *
     * 解决方案；
     * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
     * 1、配置上HttpPutFormContentFilter；
     * 2、他的作用；将请求体中的数据解析包装成一个map。
     * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
     * 员工更新方法
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
    public Msg saveEmp(Employee employee,HttpServletRequest request){
        System.out.println("请求体中的值："+request.getParameter("gender"));
        System.out.println("将要更新的员工数据："+employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }
     //分页查询
//    @RequestMapping("/emps/{pn}")
//    public String  getEmps(@PathVariable("pn")Integer pn, Model model){
////        引用PageHelper
//        PageHelper.startPage(pn,10);
//        List<Employee> list=employeeService.getAll();
//        //包装查询结果，只需交给页面就行
//        //里面封装了详细的分页信息，包括查询数据，连续显示的页数
//        PageInfo pageInfo = new PageInfo(list,5);
//        model.addAttribute("pageInfo",pageInfo);
//        return "list";
//    }
     @ResponseBody
     @RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
     public Msg deleteEmp(@PathVariable("ids")String ids){
         //批量删除
         if(ids.contains("-")){
             String[] s = ids.split("-");
             List<Integer> list = new ArrayList<>();
             for(String ss : s){
                 list.add(Integer.parseInt(ss));
             }
             employeeService.deleteBatch(list);
         }else{
             employeeService.deleteEmp(Integer.parseInt(ids));
         }
         return Msg.success();
     }
}
