package com.whale.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.web.async
 * @Description: TODO
 * @date 2019/2/13 21:25
 */
@Component
public class DeferredResultHolder {

    /**
     * map:
     * String : 订单号 ; DeferredResult<String> 对应订单的处理结果
     */
    private Map<String,DeferredResult<String>> map  = new HashMap<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
