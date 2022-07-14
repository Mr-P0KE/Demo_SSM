import com.rsk.bean.Department;
import com.rsk.bean.Employee;
import com.rsk.dao.DepartmentMapper;
import com.rsk.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test2 {
    @Autowired
     DepartmentMapper departmentMapper;
    @Autowired
     EmployeeMapper employeeMapper;

    @Autowired
     SqlSession session;
    @Test
    public void test(){

//        System.out.println(departmentMapper);
//        System.out.println("我们");

//        1.插入部门信息
  //     departmentMapper.insertSelective(new Department(null,"研发部"));
//       System.out.println(departmentMapper.insertSelective(new Department(null,"项目部")));

//        2.生成员工数据
//        employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@qq.com",1));
//          3.批量插入，使用sqlSession
//        for(){
//            employeeMapper.insertSelective(new Employee(null,"Marry","M","Jerry@qq.com",1));
//        }
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uname = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uname,"M",uname+"@qq.com",1));
        }
        System.out.println("批量添加");
    }
}
