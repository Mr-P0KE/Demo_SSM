package org;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bean.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");
        SqlSessionFactory factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        SqlSession session =  factory.openSession();
        Employee mapper = session.getMapper(Employee.class);
        System.out.println(mapper);
    }
}
