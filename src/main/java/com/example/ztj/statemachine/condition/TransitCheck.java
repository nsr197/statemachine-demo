package com.example.ztj.statemachine.condition;

import com.alibaba.cola.statemachine.Condition;
import com.example.ztj.entity.Order;
import com.example.ztj.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 惊呗
 * date 2024-01-21
 */
@Component
public class TransitCheck implements Condition<Order> {

//    @Autowired
//    private OrderService orderService;

    @Override
    public boolean isSatisfied(Order context) {

        //orderService 做校验。。。。。。。。。。。。。。。

        return true;
    }
}
