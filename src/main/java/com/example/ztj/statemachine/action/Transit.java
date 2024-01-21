package com.example.ztj.statemachine.action;

import com.alibaba.cola.statemachine.Action;
import com.example.ztj.entity.Order;
import com.example.ztj.service.OrderService;
import com.example.ztj.statemachine.event.OrderStateChangeEvent;
import com.example.ztj.statemachine.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 惊呗
 * date 2024-01-21
 */
@Component
@Slf4j
public class Transit implements Action<OrderState,OrderStateChangeEvent,Order> {

//    @Autowired
//    private OrderService orderService;

    @Override
    public void execute(OrderState from, OrderState to, OrderStateChangeEvent event, Order context) {
        log.info("订单状态流转运输中");
        context.setState(to.getState());
        //orderService 入库保存
    }
}
