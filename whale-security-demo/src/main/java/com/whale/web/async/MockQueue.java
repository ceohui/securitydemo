package com.whale.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.async
 * @Description: 模拟消息队列
 * @date 2019/2/13 0:25
 */
@Component
public class MockQueue implements Serializable {

    private String placeOrder;  //下单消息

    private String completeOrder; //订单完成后消息

    private Logger logger =  LoggerFactory.getLogger(getClass());

    public String getPlaceOrder(){
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{

            logger.info("接到下单请求"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            logger.info("下单请求完成"+placeOrder);
            //this.placeOrder = placeOrder;

        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
