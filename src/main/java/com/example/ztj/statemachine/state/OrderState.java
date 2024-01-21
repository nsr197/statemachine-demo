package com.example.ztj.statemachine.state;

public enum OrderState {
    //订单下单初始状态
    PLACE(0,"待取件"),
    //订单取件状态
    PICK_UP(1,"已取件"),
    //订单运输状态
    TRANSIT(2,"运输中"),
    //订单签收状态
    SIGN_FOR(3,"已签收");
    /**
     * 状态码
     */
    private Integer state;

    /**
     * 描述
     */
    private String depict;

    OrderState(Integer state,String depict) {
        this.state = state;
        this.depict = depict;
    }

    public Integer getState() {
        return state;
    }

    public String getDepict() {
        return depict;
    }
}