package com.example.ztj.statemachine.config;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import com.example.ztj.entity.Order;
import com.example.ztj.statemachine.event.OrderStateChangeEvent;
import com.example.ztj.statemachine.state.OrderState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 惊呗
 * date 2024-01-21
 */
@Configuration
public class OrderStatemachineConfig {

    private static String ORDER_STATE_MACHINE_NAME = "order_state";

    @Bean
    public StateMachine<OrderState, OrderStateChangeEvent, Order> build(Action<OrderState,OrderStateChangeEvent,Order> pickUp,
                                                                        Action<OrderState,OrderStateChangeEvent,Order> signFor,
                                                                        Action<OrderState,OrderStateChangeEvent,Order> transit,
                                                                        Condition<Order> pickUpCheck,
                                                                        Condition<Order> signForCheck,
                                                                        Condition<Order> transitCheck){
// 第一步：生成一个状态机builder
        StateMachineBuilder<OrderState, OrderStateChangeEvent, Order> builder = StateMachineBuilderFactory.create();
        //设置多个状态流转
//        builder.externalTransitions().fromAmong(OrderState.PLACE, States.PICK_UP, States.TRANSIT)
//                .to(States.SIGN_FOR)
//                .on(Events.TO_SIGN_FOR)
//                .when(Checks.TO_SIGN_FOR_CHECK)
//                .perform(Events.TO_SIGN_FOR.action);

        // 第二步：设置一个外部状态转移类型的builder，并设置from\to\on\when\perform
        builder.externalTransition()
                .from(OrderState.PLACE)
                .to(OrderState.PICK_UP)
                .on(OrderStateChangeEvent.PICK_UP)
                .when(pickUpCheck)
                .perform(pickUp); //这个action 我们可以按自己所需修改，比如这种Action<R,T> service的方法Service::method

        builder.externalTransition()
                .from(OrderState.PICK_UP)
                .to(OrderState.TRANSIT)
                .on(OrderStateChangeEvent.TRANSIT)
                .when(signForCheck)
                .perform(transit); //这个action 我们可以按自己所需修改，比如这种Action<R,T> service的方法Service::method

        builder.externalTransition()
                .from(OrderState.TRANSIT)
                .to(OrderState.SIGN_FOR)
                .on(OrderStateChangeEvent.SIGN_FOR)
                .when(transitCheck)
                .perform(signFor); //这个action 我们可以按自己所需修改，比如这种Action<R,T> service的方法Service::method

        // 第三步：设置状态机的id和ready，并在StateMachineFactory中的stateMachineMap进行注册
        return builder.build(ORDER_STATE_MACHINE_NAME);
    }


}
