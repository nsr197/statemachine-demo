package com.example.ztj.entity;


import com.example.ztj.statemachine.state.OrderState;
import lombok.Data;

@Data
public class Order {
    String orderNo;
    Integer state;

    public void setStateByOrderState(OrderState state) {
        setState(state.getState());
    }
}