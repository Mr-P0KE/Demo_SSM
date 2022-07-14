import com.github.pagehelper.PageInfo;
import com.rsk.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

//测试crud
@RunWith(SpringJUnit4ClassRunner.class)
//获取ioc容器
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class Test3 {

    //传入springMVC的ioc容器
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void initMokMvc(){
         mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();

        //请求成功之后，请求域中有pageInfo，取出pageInfo进行验证
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码："+ pageInfo.getPageNum());
        System.out.println("总页码："+ pageInfo.getPages());
        System.out.println("总记录数："+pageInfo.getTotal());
        System.out.println("在页面需要连续显示的页码");
        int[] a = pageInfo.getNavigatepageNums();
       for(int s : a){
           System.out.println(s);
       }

        //获取员工数据
        List<Employee> list = pageInfo.getList();
        list.forEach(emp-> System.out.println("ID: "+emp.getEmpId()+"  Name: "+emp.getEmpName()));
    }

}
