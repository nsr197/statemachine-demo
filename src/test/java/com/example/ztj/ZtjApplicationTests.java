package com.example.ztj;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.statemachine.StateMachine;
import com.example.ztj.entity.Order;
import com.example.ztj.statemachine.event.OrderStateChangeEvent;
import com.example.ztj.statemachine.state.OrderState;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ZtjApplicationTests {

    @Resource
    private StateMachine<OrderState, OrderStateChangeEvent, Order> stateMachine;

    @Test
    void contextLoads() {

        Order order = new Order();
        order.setState(OrderState.PLACE.getState());
        order.setOrderNo("TH0001");

        stateMachine.fireEvent(OrderState.PLACE, OrderStateChangeEvent.PICK_UP, order);
        log.info(JSONUtil.toJsonStr(order));

        stateMachine.fireEvent(OrderState.PICK_UP, OrderStateChangeEvent.TRANSIT, order);
        log.info(JSONUtil.toJsonStr(order));

        stateMachine.fireEvent(OrderState.TRANSIT, OrderStateChangeEvent.SIGN_FOR, order);
        log.info(JSONUtil.toJsonStr(order));

    }

}
