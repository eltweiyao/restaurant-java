package com.eltweiyao.restaurant;

import com.eltweiyao.restaurant.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantApplicationTests {

    @Autowired
    private OrderService orderService;
    @Test
    public void contextLoads() {
//        System.out.println(orderService.getTurnoverReport(1, "2bdb3993ad554d799dd3973d89b2f20e"));
    }

}
