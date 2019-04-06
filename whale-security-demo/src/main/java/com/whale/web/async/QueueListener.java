package com.whale.web.async;

import net.bytebuddy.asm.Advice;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.async
 * @Description: 监听器
 * @date 2019/2/13 21:36
 */

//ApplicationListener spring 容器初始化完毕的事件
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger =  LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(()->{

            while (true){
                if(StringUtils.isNoneBlank(mockQueue.getCompleteOrder())){
                    String orderNum = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果："+orderNum);
                    deferredResultHolder.getMap().get(orderNum).setResult("place order success");
                    mockQueue.setCompleteOrder(null);

                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }).start();

    }
}
