package com.madrix.test;

import com.madrix.dao.OperatorDao;
import com.madrix.pojo.Operator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 *
 * Created by sdc on 2018/2/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml"})
public class OperatorDaoTest {

    @Autowired
    OperatorDao operatorDao;

    @Test
    public void insert(){
        Operator operator = new Operator();
        operator.setLoginName("csd");
        operator.setPassword("csd");
        operator.setEmail("1536242521@qq.com");
        operator.setLoginTime(new Date());
        operatorDao.insert(operator);
    }
}
