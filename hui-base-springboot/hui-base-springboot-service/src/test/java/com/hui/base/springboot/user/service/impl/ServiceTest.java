package com.hui.base.springboot.user.service.impl;

import com.hui.base.springboot.service.user.HuiTestMapper;
import com.hui.base.springboot.user.mapper.OrderMapper;
import com.hui.base.springboot.user.entity.HuiTest;
import com.hui.base.springboot.user.entity.Order;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * UserServiceImpl Tester.
 *
 * @author Hu Weihui
 * @version 1.0
 * @since <pre>11/01/2018</pre>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceTest {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private HuiTestMapper testMapper;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    @Test
    public void testAdd()throws Exception{
        Order order  = new Order();
        order.setBuyQuantity(11);
        order.setOrderId("1");
        order.setOrderName("1");

        Order order2  = new Order();
        order2.setBuyQuantity(22);
        order2.setOrderId("2");
        order2.setOrderName("2");
        order2.setCreatedTime(new Date());

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        orderMapper.batchInsert(orders);
        System.out.println(order.getOrderId());
        System.out.println(order2.getOrderId());


        HuiTest test = new HuiTest();
        test.setName("hui");
        List<HuiTest> huiTests = new ArrayList<>();
        huiTests.add(test);
        testMapper.batchInsert(huiTests);
        System.out.println(test.getId());

    }

    @Test
    public void testDelete()throws Exception{
        int count = orderMapper.batchDelete(new String[]{"1", "2"});
        int testcount = testMapper.batchDelete(new BigDecimal[]{new BigDecimal(2)});
        System.out.println("----------------------");
        System.out.println(count);
        System.out.println(testcount);
        System.out.println("----------------------");

    }
    @Test
    public void testUpdate()throws Exception{
        Order order  = new Order();
        order.setBuyQuantity(11);
        order.setOrderId("1");
        order.setOrderName("xxxx");
        order.setCreatedTime(new Date());

        Order order2  = new Order();
        order2.setBuyQuantity(22);
        order2.setOrderId("2");
        order2.setOrderName("yyyy");
        order2.setCreatedTime(new Date());

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        orderMapper.batchUpdate(orders);

        HuiTest test = new HuiTest();
        test.setId(new BigDecimal(3));
        test.setName("hui2222");
        List<HuiTest> huiTests = new ArrayList<>();
        huiTests.add(test);
        testMapper.batchUpdate(huiTests);

    }
    @Test
    public void testSelect()throws Exception{
        Order order1 = new Order();
        order1.setOrderId("test1");
        order1.setOrderName("order_name");
        order1.setCreatedTime(new Date());

        orderMapper.updateByPrimaryKey(order1);
    }
}
