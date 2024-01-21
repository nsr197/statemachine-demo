package com.example.ztj.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.cola.statemachine.StateMachine;
import com.example.ztj.entity.Order;
import com.example.ztj.statemachine.event.OrderStateChangeEvent;
import com.example.ztj.statemachine.state.OrderState;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 惊呗
 * date 2024-01-21
 */
@Component
@Slf4j
public class OrderService {

    private static Map<String, Order> orderMap = new ConcurrentHashMap<>();

    @Resource
    private StateMachine<OrderState, OrderStateChangeEvent, Order> stateMachine;

    public String create() {
        Order order = new Order();
        String orderNo = IdUtil.fastSimpleUUID();
        order.setOrderNo(orderNo);
        order.setState(OrderState.PLACE.getState());
        orderMap.put(orderNo, order);
        return orderNo;
    }

    public void pickUp(String orderNo) {
        Order order = getOrder(orderNo);
        stateMachine.fireEvent(OrderState.PLACE, OrderStateChangeEvent.PICK_UP, order);
        log.info(JSONUtil.toJsonStr(order));
    }

    public void transit(String orderNo) {
        Order order = getOrder(orderNo);
        stateMachine.fireEvent(OrderState.PICK_UP, OrderStateChangeEvent.TRANSIT, order);
        log.info(JSONUtil.toJsonStr(order));
    }

    public void signFor(String orderNo) {
        Order order = getOrder(orderNo);
        stateMachine.fireEvent(OrderState.TRANSIT, OrderStateChangeEvent.SIGN_FOR, order);
        log.info(JSONUtil.toJsonStr(order));
    }

    private Order getOrder(String orderNo) {
        Order order = orderMap.get(orderNo);
        if (ObjectUtil.isNull(order)) {
            throw new RuntimeException("订单不存在");
        }
        return order;
    }


}
