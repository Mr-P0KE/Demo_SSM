package org;

import org.dao.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//测试dao层工作，推荐Spring项目是同Spring的单元测试。先导入Spring单元测试组件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test {
   @Autowired
   DepartmentMapper departmentMapper;
    @Test
    public void testCRUD(){
        //测试DepartmentMapper
//        ApplicationContext ioc =  new ClassPathXmlApplicationContext("applicationContext.xml");
////        从容器中获取mapper
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);

        System.out.println("1");
    }
}
