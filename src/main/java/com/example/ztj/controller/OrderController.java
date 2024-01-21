package com.example.ztj.controller;

/**
 * @author 惊呗
 * date 2024-01-21
 */

import com.example.ztj.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/create")
    public String create(){
        return orderService.create();
    }

    @GetMapping("/pickUp")
    public void pickUp(String orderNo){
        orderService.pickUp(orderNo);
    }

    @GetMapping("/transit")
    public void transit(String orderNo){
        orderService.transit(orderNo);
    }

    @GetMapping("/signFor")
    public void signFor(String orderNo){
        orderService.signFor(orderNo);
    }
}
